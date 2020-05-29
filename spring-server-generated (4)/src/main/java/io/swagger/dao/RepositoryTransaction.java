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
}