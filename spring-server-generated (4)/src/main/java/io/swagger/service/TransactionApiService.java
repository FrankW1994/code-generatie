package io.swagger.service;

import io.swagger.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionApiService {

    List<Transaction> transactions = new ArrayList<>(
            Arrays.asList(
                    new Transaction(1, "NL11INHO11111111", "NL22INHO222222", "03/04/2020", 140.56),
                    new Transaction(2, "NL77INHO77777777", "NL22INHO222222", "03/04/2020", 9.00),
                    new Transaction(3, "NL33INHO33333333", "NL44INHO444444", "01/01/2020", 100.00)
            )
    );

    public List<Transaction> createTransaction(Transaction transactionCreated){
        transactions.add(transactionCreated);
        return transactions;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction getTransaction(String accept) {
        for(Transaction transaction : transactions)
        {
            if(transaction.getTransactionId().toString() == accept)
            {
                return transaction;
            }
        }
        return new Transaction(1, "NL11INHO11111111", "NL22INHO222222", "03/04/2020", 140.56);
    }
}
