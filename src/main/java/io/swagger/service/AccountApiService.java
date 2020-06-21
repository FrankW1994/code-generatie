package io.swagger.service;

import io.swagger.dao.RepositoryAccount;
import io.swagger.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class AccountApiService {

    @Autowired
    private RepositoryAccount repositoryAccount;

    Account accError = new Account("NLFOUT");

    public AccountApiService()
    {

    }

    //get /accounts
    public List<Account> getAllAccounts() {
        return (List<Account>) repositoryAccount.findAll();
    }

    // post /accounts
    public Account addAccount(Account body) {
        repositoryAccount.save(body);
        System.out.println(body);
        return body;
    }

    // Get /accounts/Iban
    public Account getAccountFromIBAN(String ibanReceiver) {

        List<Account> allAccounts = (List<Account>) repositoryAccount.findAll();
        for(Account a : allAccounts)
        {
            if(a.getIBAN().equals(ibanReceiver))
            { return a; }
        }

        return null;
    }

    // Delete /accounts/Iban
    public void deleteAccountFromIBAN(String ibanReceiver){
        repositoryAccount.DeleteAccount(ibanReceiver);
    }

    // Post /accounts/iban/deposit
    public Account depositAccount(String ibanReceiver, double deposit){
        double balance = repositoryAccount.GetBalance(ibanReceiver) + deposit;
        repositoryAccount.UpdateNewBalance(balance,ibanReceiver);
        return repositoryAccount.findById(ibanReceiver).get();
    }

    // Post /accounts/iban/deposit
    public Account withdrawAccount(String ibanReceiver, double withdraw){
        double balance = repositoryAccount.GetBalance(ibanReceiver) - withdraw;
        repositoryAccount.UpdateNewBalance(balance,ibanReceiver);
        return repositoryAccount.findById(ibanReceiver).get();
    }

    public void updateNewBalanceServiceAccounts(double NewBalance, String IBAN) {
        repositoryAccount.UpdateNewBalance(NewBalance, IBAN);
    }

    public Double getBalanceOfAccount(String ibanSender) {
        List<Account> allAccounts = (List<Account>) repositoryAccount.findAll();
        for(Account a : allAccounts)
        {
            if(a.getIBAN().equals(ibanSender))
            { return a.getBalance(); }
        }

        return null;
    }
}
