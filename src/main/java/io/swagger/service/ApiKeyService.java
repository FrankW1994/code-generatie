package io.swagger.service;

import io.swagger.dao.RepositoryApiKey;
import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApiKeyService {

    @Autowired
    private RepositoryApiKey repositoryApiKey;

    public ApiKeyService() {
    }

    public Optional<ApiKey> findById(String authKey) {
        return repositoryApiKey.findById(authKey);
    }
}


