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

    @Query(value = "select t from Transaction t join User u where u.id =:userId", nativeQuery = true)
    List<Transaction> getTransactionsFromUserId(@Param("userId") String userId);

    @Query("select t from Transaction t where t.ibanSender = :iban or t.ibanReceiver =:iban")
    List<Transaction> getTransactionsFromIBAN(@Param("iban") String iban);

    @Query("select t from Transaction t where t.transferAmount =:transactionAmount")
    List<Transaction> getTransactionsFromAmount(@Param("transactionAmount") Double transactionAmount);
}