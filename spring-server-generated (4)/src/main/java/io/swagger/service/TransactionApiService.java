package io.swagger.service;

import io.swagger.dao.RepositoryTransaction;
import io.swagger.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionApiService {

    @Autowired
    private RepositoryTransaction repositoryTransaction;

    Transaction transError = new Transaction("NLFOUT");

    public TransactionApiService() {}

    public List<Transaction> getTransactions() {
        return (List<Transaction>) repositoryTransaction.findAll();
    }

    public Transaction getTransaction(Long transactionId) {
        return repositoryTransaction.findOne(transactionId);
    }

//    public Transaction getTransactionsFromName(String usernameSender) {
//        for(Transaction transaction : transactions)
//        {
//            if (transaction.getNameSender().equals(usernameSender))
//            {
//                return transaction;
//            }
//        }
//        return transError;
//    }
//
//    public List<Transaction> getTransactionsFromIBAN(String IBAN) {
//        List<Transaction> ListOfSendersOrReceivers = new ArrayList<>();
//        for(Transaction transaction : transactions)
//        {
//            if (transaction.getIbanSender().equals(IBAN))
//            {
//                ListOfSendersOrReceivers.add(transaction);
//            }
//            if(transaction.getIbanReceiver().equals(IBAN))
//            {
//                ListOfSendersOrReceivers.add(transaction);
//            }
//        }
//        return ListOfSendersOrReceivers;
//    }
//
//    public Transaction getTransactionsFromDates(LocalDate transactionSearchDateStart, LocalDate transactionSearchDateEnd) {
//        for(Transaction transaction : transactions)
//        { // Nog aanpassen voor of het tussen het begin en eind zit (?) string
//            if (transaction.getTransactionDate().equals(transactionSearchDateStart) || transaction.getTransactionDate().equals(transactionSearchDateEnd))
//            {
//                return transaction;
//            }
//        }
//        return transError;
//    }
//
//    public List<Transaction>  getTransactionsFromAmount(Double transactionAmount) {
//        List<Transaction> matchingtransactions = new ArrayList<>();
//        for(Transaction transaction : transactions)
//        {
//            if (transaction.getTransferAmount().equals(transactionAmount))
//            {
//                matchingtransactions.add(transaction);
//            }
//        }
//        return matchingtransactions;
//    }
//
//    public Transaction makeTransaction(Transaction transaction) {
//        try{
//            /// Use account services to
//            /// Find account for receiver Find account for sender
//            Account receiver = accountApiService.getAccountFromIBAN(transaction.getIbanReceiver());
//
//            Account sender = accountApiService.getAccountFromIBAN(transaction.getIbanSender());
//
//
//            /// check is account is nog enum type blocked or something
//
//            // Apply update new balance for account receiver
//            Double Rbalance = receiver.getBalance();
//            Double Sbalance = sender.getBalance();
//
//            Rbalance += transaction.getTransferAmount();
//            Sbalance -= transaction.getTransferAmount();
//
//            receiver.setBalance(Rbalance);
//            sender.setBalance(Sbalance);
//
//            // UPDATE accounts in database
//  //          accountApiService.updateNewBalanceServiceAccounts(receiver, sender);
//
//            return transaction;
//        }catch(Exception ex){
//            System.out.println(ex);
//            return transError;
//        }
//    }

}
