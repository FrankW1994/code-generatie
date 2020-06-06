package io.swagger.dao;

import io.swagger.model.Account;
import io.swagger.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RepositoryUser extends CrudRepository<User, Long> {

    @Transactional
    @Modifying
    @Query("delete from User u where u.id =:userId")
    User deleteUser(@Param("userId") String userId);
}