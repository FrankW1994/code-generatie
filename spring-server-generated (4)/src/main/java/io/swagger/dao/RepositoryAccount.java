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
    @Query(value = "UPDATE Account a SET a.balance = :balance WHERE a.IBAN = :IBAN")
    Account UpdateNewBalance(@Param("balance") double newBalance, @Param("IBAN") String IBAN);

    @Transactional
    @Modifying
    @Query("delete from Account a where a.IBAN =:IBAN")
    Account DeleteAccount(@Param("IBAN") String IBAN);

    @Transactional
    @Modifying
    @Query("SELECT a.balance FROM Account a where a.IBAN =:IBAN")
    double GetBalance(@Param("IBAN") String IBAN);
}