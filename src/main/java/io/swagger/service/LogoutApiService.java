package io.swagger.service;

import io.swagger.dao.RepositoryApiKey;
import io.swagger.dao.RepositoryLogin;
import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LogoutApiService {

    @Autowired
    private RepositoryApiKey repositoryApiKey;

    public void logoutUser(String username)
    {
        repositoryApiKey.deleteByUsername(username);
        System.out.println("succesfull logout");
    }
}
