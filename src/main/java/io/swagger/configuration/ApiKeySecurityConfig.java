package io.swagger.configuration;

import io.swagger.dao.RepositoryApiKey;
import io.swagger.dao.RepositoryUser;
import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import io.swagger.model.User;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@EnableWebSecurity
@Log
public class ApiKeySecurityConfig extends WebSecurityConfigurerAdapter {

    private RepositoryApiKey repositoryApiKey;

    private RepositoryUser repositoryUser;

    public ApiKeySecurityConfig(RepositoryApiKey repository, RepositoryUser repositoryUser)
    {
        this.repositoryApiKey = repository;
        this.repositoryUser = repositoryUser;
    }

    @Value("${bankshop.api.token.header-name}")
    private String headerName;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principle = (String) authentication.getPrincipal();

            if(!repositoryApiKey.findById(principle).isPresent())
            {   throw new BadCredentialsException("Api Key was not found in the systems");
            }

            authentication.setAuthenticated(true);
            return authentication;
        });

        http
                .antMatcher("/transactions")
              //  .antMatcher("/login")
                .csrf().disable() // disable X-site forgery
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // every new request needs auth
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();

    }

    public ApiKey ValidateUserAndReturnApiKey(Login login)
    {
        User user;
        if((user = repositoryUser.findUserByUserCredentials(login.getUsername(), login.getPassword())) == null)
            return null;
        ApiKey apiKey = repositoryApiKey.findApiKeyByUser(user.getId());
        //user already has token
        if(apiKey != null) {
            return apiKey;
        }
        //token will expire after 30min from now
        apiKey = new ApiKey(CreateApiKey(), user.getId(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
        repositoryApiKey.save(apiKey);

        return apiKey;
    }


    private String CreateApiKey()
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        String token = "";

        List<String> tokenParts = new ArrayList<>();
        tokenParts.add("");
        tokenParts.add("");
        tokenParts.add("");
        tokenParts.add("");

        int partCounter = 1;
        for(String part : tokenParts) {
            for (int i = 1; i < 5; i++) {
                if(partCounter % 2 == 1 )
                    part += Integer.toString(rand.nextInt(10));
                else
                    part += alphabet.charAt(rand.nextInt(alphabet.length()));
                if(i == 4) {
                    token += "-" + part;
                    partCounter++;
                }

            }
        }
        //remove first -
        token = token.replaceFirst("-", "");
        return token;
    }

}
