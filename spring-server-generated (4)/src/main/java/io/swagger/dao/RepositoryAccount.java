package io.swagger.dao;

import io.swagger.model.Account;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RepositoryAccount extends CrudRepository<Account, String> {

    @Transactional
    @Modifying
    @Query("update Account a set a.balance =:balance where a.userId =:userId")
    void UpdateNewBalance(@Param("balance") double NewBalance, @Param("userId") Long userId);

    @Transactional
    @Modifying
    @Query("delete from Account a where a.userId =:userId")
    void DeleteAccount(@Param("userId") Long userId);
}