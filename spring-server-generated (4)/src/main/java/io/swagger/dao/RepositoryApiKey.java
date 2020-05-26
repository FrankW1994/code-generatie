package io.swagger.dao;

import io.swagger.model.ApiKey;
import org.springframework.data.repository.CrudRepository;

public interface RepositoryApiKey extends CrudRepository<ApiKey, String> {

}
