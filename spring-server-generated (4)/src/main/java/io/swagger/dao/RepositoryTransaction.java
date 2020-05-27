package io.swagger.dao;

import io.swagger.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryTransaction extends CrudRepository<Transaction, Long> {
}
