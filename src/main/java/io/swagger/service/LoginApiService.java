package io.swagger.service;

import io.swagger.dao.RepositoryApiKey;
import io.swagger.dao.RepositoryLogin;
import io.swagger.dao.RepositoryUser;
import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class LoginApiService {

    @Autowired
    private RepositoryLogin repositoryLogin;

    @Autowired
    private RepositoryApiKey repositoryApiKey;

    //public UserApiService{}

    public ApiKey loginUser(Login login){
        System.out.println(login);
        Login currentLogin = repositoryLogin.findOne(login.getUsername());
        if (!currentLogin.getUsername().isEmpty()){
            if (BCrypt.checkpw(login.getPassword(), currentLogin.getPassword()))
            {
                repositoryLogin.findOne(login.getUsername());
                //repositoryLogin.save(login);
                UUID uuid = UUID.randomUUID();
                ApiKey apiKey = new ApiKey(2L, uuid.toString(), login.getUsername());

                repositoryApiKey.save(apiKey);
                //repositoryLogin.findAll().forEach(System.out::println);
                System.out.println("login succes");
                return apiKey;
            }
        }
        System.out.println("login failed");
        return null;
    }
}
