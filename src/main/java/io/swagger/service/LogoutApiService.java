package io.swagger.service;

import io.swagger.dao.RepositoryApiKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogoutApiService {

    @Autowired
    private RepositoryApiKey repositoryApiKey;

    public void logoutUser(String username)
    {
   //     repositoryApiKey.deleteByUsername(username);
        System.out.println("succesfull logout");
    }
}
