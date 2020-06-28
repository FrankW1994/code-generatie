package io.swagger.service;

import io.swagger.dao.RepositoryTransaction;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class TransactionApiService {

    @Autowired
    private RepositoryTransaction repositoryTransaction;

    @Autowired
    private AccountApiService accountApiService;

    @Autowired
    private  UserApiService userApiService;

    public TransactionApiService() {
    }

    public List<Transaction> getTransactions() {
        return (List<Transaction>) repositoryTransaction.findAll();
    }

    public Transaction getTransaction(Long transactionId) {
        return repositoryTransaction.findById(transactionId).get();
    }

    public Boolean makeTransaction(Transaction transaction) {
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
            accountApiService.updateNewBalanceServiceAccounts(receiver.getBalance(), receiver.getIBAN());
            accountApiService.updateNewBalanceServiceAccounts(sender.getBalance(), sender.getIBAN());

            // now the transaction was successful save the transaction
            repositoryTransaction.save(transaction);
            return true;
            }catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }


    public void checkValidTransaction(Transaction body) throws Exception {
        Account accountSender = accountApiService.getAccountFromIBAN(body.getIbanSender());
        Account accountReceiver = accountApiService.getAccountFromIBAN(body.getIbanReceiver());
        if ((accountSender == null) || accountReceiver == null) {
            throw new Exception("Account sender or receiver does not exists!");
            //  Account does not exists!
        }
        //	The maximum amount per transaction cannot be higher than a predefined number, referred to a transaction limit
        if(userApiService.getById(accountSender.getUserId()).getRank() == User.RankEnum.CUSTOMER){
            if(accountSender.isPassedCumulativeTransactions()) {
                throw new Exception("Account sender overstayed transactions per day.");
            }
        }
        // Make more transaction daily limit

        if ((body.getTransferAmount() < 0) || (body.getTransferAmount() >= accountSender.getDailyLimit())) {
            throw new Exception("Transaction must be between 0 and " + accountSender.getDailyLimit().toString() + "!");
            //    Transaction must be between 0 and the daily limit

        } else if (body.getTransferAmount() > (accountSender.getBalance() - 500)) {
            throw new Exception("Sender account has not enough money");
            //     Account has not enough money
        }
        // Checks if accounts are from type saving and if so, has same owner then proceed transaction,
        if(!IsTransactionAllowed(accountSender, accountReceiver))
        { throw new Exception("Transactions from or to a savings account must be from the owner"); }
    }

    // Checks if account is a saving and if so is account from same owner allowed to proceed,
    private Boolean IsTransactionAllowed(Account sender, Account receiver) {
        if((sender.getRank() == Account.RankEnum.SAVING) || (receiver.getRank() == Account.RankEnum.SAVING))
        {
            if(receiver.getUserId() == sender.getUserId())
            {
                // Account is linked with same owner. Allow transactions to be made
                return true;
            }

            // Saving does NOT  have same owner, must return false
            return false;
        }
        // Account is not saving
        return true;
    }

    public List<Transaction> getTransactionsFromName(String username) {
        return repositoryTransaction.findAllWithUsername(username);
    }

    public List<Transaction> getTransactionsFromIBAN(String IBAN) {
        return repositoryTransaction.getTransactionsFromIBAN(IBAN);
    }

    public List<Transaction> getTransactionsFromAmount(Double transferAmount) {
        return repositoryTransaction.getTransactionsFromAmount(transferAmount);
    }

    public List<Transaction> FindAllMatches(String nameSender, Long transactionId, String IBAN, Double transferAmount, Integer maxNumberOfResults) {
        List<Transaction> myList = new ArrayList<Transaction>();
        if (nameSender != null) {
            for (Transaction t : getTransactionsFromName(nameSender)) {
                myList.add(t);
            }
        }
        if (transactionId != null) {
             Transaction transaction = repositoryTransaction.findById(transactionId).get();
            if(transaction != null) { myList.add(transaction);  }
        }
        if (IBAN != null) {
            for(Transaction t : getTransactionsFromIBAN(IBAN))
            {
                myList.add(t);
            }
        }
        if (transferAmount != null) {
            List<Transaction> transactions = getTransactionsFromAmount(transferAmount);
            for(Transaction t : transactions) {
                myList.add(t);
            }
        }
        if (myList.size() == 0) {
            myList = getTransactions();
        }
        if ((maxNumberOfResults != null) && (maxNumberOfResults < myList.size())) {
            myList = myList.subList(0, maxNumberOfResults);
        }

        return myList;
    }
}
