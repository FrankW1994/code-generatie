package io.swagger.service;

import io.swagger.dao.RepositoryTransaction;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

            if((sender.getRank() == Account.RankEnum.SAVING) || (receiver.getRank() == Account.RankEnum.SAVING))
            {
               if(receiver.getUserId() == sender.getUserId())
               {
                   // Calculate new balance for account receiver
                   Double Rbalance = receiver.getBalance();
                   Double Sbalance = sender.getBalance();

                   Rbalance += transaction.getTransferAmount();
                   Sbalance -= transaction.getTransferAmount();

                   receiver.setBalance(Rbalance);
                   sender.setBalance(Sbalance);

                   // UPDATE accounts in database with included new balance
                   accountApiService.updateNewBalanceServiceAccounts(receiver.getBalance(), receiver.getIBAN());
                   accountApiService.updateNewBalanceServiceAccounts(sender.getBalance(), sender.getIBAN());

                   // now the transaction was successful save the transaction
                   return repositoryTransaction.save(transaction);

               }
               else { throw new Exception("Can't transfer from or to SAVINGS account from an extern account."); }
            }
            else
            {
                // Calculate new balance for account receiver
                Double Rbalance = receiver.getBalance();
                Double Sbalance = sender.getBalance();

                Rbalance += transaction.getTransferAmount();
                Sbalance -= transaction.getTransferAmount();

                receiver.setBalance(Rbalance);
                sender.setBalance(Sbalance);

                // UPDATE accounts in database with included new balance
                accountApiService.updateNewBalanceServiceAccounts(receiver.getBalance(), receiver.getIBAN());
                accountApiService.updateNewBalanceServiceAccounts(sender.getBalance(), sender.getIBAN());

                // now the transaction was successful save the transaction
                return repositoryTransaction.save(transaction);
            }
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public List<Transaction> getTransactionsFromName(String username) {
        return repositoryTransaction.findAllWithUsername(username);
    }

    public List<Transaction> getTransactionsFromTransactionId(Long transactionId) {
        return repositoryTransaction.getTransactionsFromTransactionId(transactionId);
    }

    public List<Transaction> getTransactionsFromIBAN(String IBAN) {
        return repositoryTransaction.getTransactionsFromIBAN(IBAN);
    }

    public List<Transaction> getTransactionsFromAmount(Double transactionAmount) {
        return repositoryTransaction.getTransactionsFromAmount(transactionAmount);
    }
}
