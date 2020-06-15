package io.swagger.dao;

import io.swagger.model.Transaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryTransaction extends CrudRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.transferAmount >=:transferAmount")
    List<Transaction> FindTransactionsOver(@Param("transferAmount") double amount);

    @Query("select t from Transaction t where t.nameSender = :username")
    List<Transaction> findAllWithUsername(@Param("username") String username);

    @Query("select t from Transaction t where t.ibanSender =:IBAN OR t.ibanReceiver =:IBAN")
    List<Transaction> getTransactionsFromIBAN(@Param("IBAN") String IBAN);

    @Query("select t from Transaction t where t.transferAmount =:transactionAmount")
    List<Transaction> getTransactionsFromAmount(@Param("transactionAmount") Double transactionAmount);

    @Query("select t from Transaction t where t.transactionId =:transactionId")
    List<Transaction> getTransactionsFromTransactionId(@Param("transactionId") Long transactionId);
}