package io.swagger.service;

import io.swagger.dao.RepositoryApiKey;
import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LoginApiService {

    @Autowired
    private RepositoryApiKey repositoryApiKey;

    @Autowired
    private UserApiService userApiService;

    public LoginApiService(){}

    public ApiKey loginUser(Login login){
        if (!login.getUsername().isEmpty()){
            User user =  userApiService.getUser(login.getUsername(), login.getPassword());

            if (BCrypt.checkpw(login.getPassword(), user.getPassword()))
            {
                if(user != null) {
                    UUID uuid = UUID.randomUUID();
                    ApiKey apiKey = new ApiKey(uuid.toString(), user.getId());
                    repositoryApiKey.save(apiKey);
                    System.out.println("login succes");
                    return apiKey;
                }
            }
        }
        System.out.println("login failed");
        return null;
    }
}
