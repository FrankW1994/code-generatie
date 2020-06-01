package io.swagger.service;

import io.swagger.dao.RepositoryAccount;
import io.swagger.dao.RepositoryTransaction;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionApiService {

    @Autowired
    private RepositoryTransaction repositoryTransaction;

    @Autowired
    private AccountApiService accountApiService;

    public TransactionApiService() {
    }

    public List<Transaction> getTransactions() {
        return (List<Transaction>) repositoryTransaction.findAll();
    }

    public Transaction getTransaction(Long transactionId) {
        return repositoryTransaction.findOne(transactionId);
    }

    public Transaction makeTransaction(Transaction transaction) {
   //      check if account exists AND if account is not BLOCKED
        try{
            /// Use account services to
            /// Find account receiver & sender
            Account receiver = accountApiService.getAccountFromIBAN(transaction.getIbanReceiver());
            Account sender = accountApiService.getAccountFromIBAN(transaction.getIbanSender());

            // Calculate new balance for account receiver
            Double Rbalance = receiver.getBalance();
            Double Sbalance = sender.getBalance();

            Rbalance += transaction.getTransferAmount();
            Sbalance -= transaction.getTransferAmount();

            receiver.setBalance(Rbalance);
            sender.setBalance(Sbalance);

            // UPDATE accounts in database with included new balance
            accountApiService.updateNewBalanceServiceAccounts(receiver.getBalance(), receiver.getUserId());
            accountApiService.updateNewBalanceServiceAccounts(sender.getBalance(), sender.getUserId());

            // now the transaction was successful save the transaction
            return repositoryTransaction.save(transaction);
        }catch(Exception ex){
            System.out.println(ex);
            return null;
        }
    }

    public List<Transaction> getTransactionsFromName(String username) {
        return repositoryTransaction.findAllWithUsername(username);
    }

    public List<Transaction> getTransactionsFromUserId(String userId) {
        return repositoryTransaction.getTransactionsFromUserId(userId);
    }

    public List<Transaction> getTransactionsFromIBAN(String iban) {
        return repositoryTransaction.getTransactionsFromIBAN(iban);
    }

    public List<Transaction> getTransactionsFromAmount(Double transactionAmount) {
        return repositoryTransaction.getTransactionsFromAmount(transactionAmount);
    }
}
