package io.swagger.service;

import io.swagger.model.Account;
import io.swagger.model.Transaction;
import io.swagger.model.User;
import org.springframework.stereotype.Service;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDate;
import org.threeten.bp.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class TransactionApiService {

    public AccountApiService accountApiService = new AccountApiService();

    Transaction transError = new Transaction("NLFOUT");



    List<Transaction> transactions = new ArrayList<>(
            Arrays.asList(
                    new Transaction(1L, "NL11INHO11111111", "NL22INHO222222", "GPOSSEL", "03/04/2020", 140D),
                    new Transaction(2L, "NL77INHO77777777", "NL22INHO222222", "SBOERE", "03/04/2020", 9D),
                    new Transaction(3L, "NL33INHO33333333", "NL44INHO444444", "TWUBBEN", "01/01/2020", 100D)
            )
    );

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public Transaction getTransaction(Long transactionId) {
        for(Transaction transaction : transactions)
        {
            if (transaction.getTransactionId().equals(transactionId))
            {
                return transaction;
            }
        }
        return transError;
    }

    public Transaction getTransactionsFromName(String usernameSender) {
        for(Transaction transaction : transactions)
        {
            if (transaction.getNameSender().equals(usernameSender))
            {
                return transaction;
            }
        }
        return transError;
    }

    public List<Transaction> getTransactionsFromIBAN(String IBAN) {
        List<Transaction> ListOfSendersOrReceivers = new ArrayList<>();
        for(Transaction transaction : transactions)
        {
            if (transaction.getIbanSender().equals(IBAN))
            {
                ListOfSendersOrReceivers.add(transaction);
            }
            if(transaction.getIbanReceiver().equals(IBAN))
            {
                ListOfSendersOrReceivers.add(transaction);
            }
        }
        return ListOfSendersOrReceivers;
    }

    public Transaction getTransactionsFromDates(LocalDate transactionSearchDateStart, LocalDate transactionSearchDateEnd) {
        for(Transaction transaction : transactions)
        { // Nog aanpassen voor of het tussen het begin en eind zit (?) string
            if (transaction.getTransactionDate().equals(transactionSearchDateStart) || transaction.getTransactionDate().equals(transactionSearchDateEnd))
            {
                return transaction;
            }
        }
        return transError;
    }

    public List<Transaction>  getTransactionsFromAmount(Double transactionAmount) {
        List<Transaction> matchingtransactions = new ArrayList<>();
        for(Transaction transaction : transactions)
        {
            if (transaction.getTransferAmount().equals(transactionAmount))
            {
                matchingtransactions.add(transaction);
            }
        }
        return matchingtransactions;
    }

    public Transaction makeTransaction(Transaction transaction) {
        try{
            /// Use account services to
            /// Find account for receiver Find account for sender
            Account receiver = accountApiService.getAccountFromIBAN(transaction.getIbanReceiver());

            Account sender = accountApiService.getAccountFromIBAN(transaction.getIbanSender());


            /// check is account is nog enum type blocked or something

            // Apply update new balance for account receiver
            Double Rbalance = receiver.getBalance();
            Double Sbalance = sender.getBalance();

            Rbalance += transaction.getTransferAmount();
            Sbalance -= transaction.getTransferAmount();

            receiver.setBalance(Rbalance);
            sender.setBalance(Sbalance);

            // UPDATE accounts in database
            accountApiService.updateNewBalanceServiceAccounts(receiver, sender);

            return transaction;
        }catch(Exception ex){
            System.out.println(ex);
            return transError;
        }
    }

}
