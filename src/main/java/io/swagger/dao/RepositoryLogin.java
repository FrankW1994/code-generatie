package io.swagger.dao;

import io.swagger.model.ApiKey;
import io.swagger.model.Login;
import io.swagger.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryLogin extends CrudRepository<Login, String> {

}