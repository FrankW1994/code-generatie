package io.swagger.dao;

import io.swagger.model.ApiKey;
import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface RepositoryApiKey extends CrudRepository<ApiKey, String> {
    @Transactional
    @Modifying
    @Query("DELETE FROM ApiKey key where key.username = :username")
    void deleteByUsername(@Param("username") String username);
}
