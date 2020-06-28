package io.swagger.configuration;

import io.swagger.dao.RepositoryApiKey;
import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import io.swagger.model.User;
import io.swagger.service.UserApiService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configuration
@EnableWebSecurity
@Log
public class ApiKeySecurityConfig extends WebSecurityConfigurerAdapter {

    public final UserApiService userApiService;
    private RepositoryApiKey repositoryApiKey;
    @Value("${bankshop.api.token.header-name}")
    private String headerName;

    public ApiKeySecurityConfig(RepositoryApiKey repository, UserApiService userApiService) {
        this.repositoryApiKey = repository;
        this.userApiService = userApiService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        ApiKeyAuthFilter filter = new ApiKeyAuthFilter(headerName);
        filter.setAuthenticationManager(authentication -> {
            String principle = (String) authentication.getPrincipal();

            if (!repositoryApiKey.findById(principle).isPresent()) {
                throw new BadCredentialsException("Api Key was not found in the systems");
            }

            authentication.setAuthenticated(true);
            return authentication;
        });

        http
                .antMatcher("/transactions")
                //  .antMatcher("/login")
                .antMatcher("/users")
                .csrf().disable() // disable X-site forgery
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS) // every new request needs auth
                .and()
                .addFilter(filter)
                .authorizeRequests()
                .anyRequest()
                .authenticated();

    }

    public ApiKey ValidateUserAndReturnApiKey(Login login) throws Exception {
        if (!login.getUsername().isEmpty()) {
            User user = userApiService.getUser(login.getUsername());
            user.setPasswordEncrypt(user.getPassword());
            if (user != null) {
                if (BCrypt.checkpw(login.getPassword(), user.getPassword())) {
                    ApiKey apiKey = repositoryApiKey.findApiKeyByUser(user.getId());
                    //user already has token
                    if (apiKey != null) {
                        //token will expire after 30min from now
                        apiKey = new ApiKey(apiKey.getApiKey(), user.getId(), LocalDateTime.now(), LocalDateTime.now().plusMinutes(30));
                        return apiKey;
                    }
                    throw new Exception("No ApiKey made for user!");
                }
                throw new Exception("Bad password!");
            }
            throw new Exception("No user found with username!");
        }
        throw new Exception("Username is empty!");
    }


    private String CreateApiKey() {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rand = new Random();
        String token = "";

        List<String> tokenParts = new ArrayList<>();
        tokenParts.add("");
        tokenParts.add("");
        tokenParts.add("");
        tokenParts.add("");

        int partCounter = 1;
        for (String part : tokenParts) {
            for (int i = 1; i < 5; i++) {
                if (partCounter % 2 == 1)
                    part += Integer.toString(rand.nextInt(10));
                else
                    part += alphabet.charAt(rand.nextInt(alphabet.length()));
                if (i == 4) {
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
