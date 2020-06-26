package io.swagger.dao;

import io.cucumber.java.Before;
import io.swagger.model.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TransactionRepositoryTest {

    @Autowired
    private RepositoryTransaction repositoryTransaction;

    @Before()
    public void setUp() throws Exception{
    }

    @Test
    public void findAllWithUsernameShouldReturnNotNull() throws Exception{
    List<Transaction> transactions = repositoryTransaction.findAllWithUsername("GPOSSEL");
    assertNotNull(transactions);
    }

    @Test
    public void getTransactionsFromIBANShouldReturnNotNull() throws Exception{
        List<Transaction> transactions = repositoryTransaction.getTransactionsFromIBAN("GPOSSEL");
        assertNotNull(transactions);
    }

    @Test
    public void getTransactionsFromAmountShouldReturnNotNull() throws Exception{
        List<Transaction> transactions = repositoryTransaction.getTransactionsFromAmount(140d);
        assertNotNull(transactions);
    }

    @Test
    public void getTransactionsFromTransactionId() throws Exception {
        Optional<Transaction> transactions = repositoryTransaction.findById(1000001l);
        assertNotNull(transactions);
    };
}
