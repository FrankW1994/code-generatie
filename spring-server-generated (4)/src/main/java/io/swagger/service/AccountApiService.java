package io.swagger.service;

import io.swagger.dao.RepositoryAccount;
import io.swagger.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;


@Service
public class AccountApiService {

    @Autowired
    private RepositoryAccount repositoryAccount;

    Account accError = new Account("NLFOUT");

    public AccountApiService()
    {}


    public Account getAccountFromIBAN(String ibanReceiver) {
            return repositoryAccount.findOne(ibanReceiver);
    }

    public List<Account> getAllAccounts() {
            return (List<Account>) repositoryAccount.findAll();
    }

    public Account addAccount(Account body) {
        repositoryAccount.save(body);
        System.out.println(body);
        return body;
    }

    public void updateNewBalanceServiceAccounts(double NewBalance, Long userId) {
        repositoryAccount.UpdateNewBalance(NewBalance, userId);
    }
}
