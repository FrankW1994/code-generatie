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
        return repositoryAccount.findOne(ibanReceiver);
    }

    // Delete /accounts/Iban
    public Account deleteAccountFromIBAN(String ibanReceiver){
        return repositoryAccount.DeleteAccount(ibanReceiver);
    }

    // Post /accounts/iban/deposit
    public Account depositAccount(String ibanReceiver, double deposit){
        double balance = repositoryAccount.GetBalance(ibanReceiver) + deposit;
        return repositoryAccount.UpdateNewBalance(balance,ibanReceiver);
    }

    // Post /accounts/iban/deposit
    public Account withdrawAccount(String ibanReceiver, double withdraw){
        double balance = repositoryAccount.GetBalance(ibanReceiver) - withdraw;
        return repositoryAccount.UpdateNewBalance(balance,ibanReceiver);
    }

    public void updateNewBalanceServiceAccounts(double NewBalance, String IBAN) {
        repositoryAccount.UpdateNewBalance(NewBalance, IBAN);
    }


}
