package io.swagger.service;

import io.swagger.api.NotFoundException;
import io.swagger.model.Account;
import io.swagger.model.Transaction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static io.swagger.model.Account.RankEnum.CURRENT;
import static io.swagger.model.Account.RankEnum.SAVING;
import static io.swagger.model.Account.StatusEnum.ACTIVE;
import static io.swagger.model.Account.StatusEnum.BLOCKED;


@Service
public class AccountApiService {

    Account accError = new Account("NLFOUT");

    public AccountApiService()
    {}

    List<Account> accounts = new ArrayList<>(
            Arrays.asList(
                    new Account(1L, "NL77INHO77777777", CURRENT, ACTIVE, 1660.00D, "EUR"),
                    new Account(2L, "NL22INHO222222", SAVING, ACTIVE, 5504.00D, "EUR"),
                    new Account(3L, "NL33INHO33333333", CURRENT,  BLOCKED, 604.00D, "EUR"),
                    new Account(14L, "NL11INHO11111111", CURRENT, ACTIVE, 1700.00D, "EUR")
            )
    );

    public Account getAccountFromIBAN(String ibanReceiver) {
        for(Account account : accounts) {
            if (account.getIBAN().equals(ibanReceiver)) {
                return account;
            }
        }
        throw new NoSuchElementException("Account NLXXINHOXXXXXXXX does not exists");
      //  return accError;
    }

    public void updateNewBalanceServiceAccounts(Account receiver, Account sender) {

        /// UPDATE in databse (DATAbase not yet available use list
        for(Account a : accounts)
        {
            if(a.getIBAN().equals(receiver.getIBAN()))
            {
                a = receiver;
            }
            if(a.getIBAN().equals(sender.getIBAN()))
            {
                a = sender;
            }
        }
    }

    public List<Account> getAccounts() {

        if(accounts.size() == 0)
        {
            throw new IllegalStateException("List is empty..");
        }

        return accounts;  }

    public List<Account> FilterOnRankOfAccounts(String rankOfAccount) {
        List<Account> ofTypeRank = new ArrayList<>();

        // make filter for database nog service
        for(Account a : accounts)
        {
            Account.RankEnum value = Account.RankEnum.fromValue(rankOfAccount);
            if(a.getRank() == value)
            {
                ofTypeRank.add(a);
            }
        }
        return ofTypeRank;
    }

    public List<Account> FilterOnStatusOfAccounts(String statusOfAccount) {
        List<Account> ofTypeStatus = new ArrayList<>();

        // make filter for database nog service
        for(Account a : accounts)
        {
            Account.StatusEnum value = Account.StatusEnum.fromValue(statusOfAccount);
            if(a.getStatus() == value)
            {
                ofTypeStatus.add(a);
            }
        }
        return ofTypeStatus;
    }

    public List<Account> FilterOnBalanceAccounts(Double balance) {
        Double start = balance * 0.90;
        Double end = balance * 1.10;
        List<Account> accountMatching = new ArrayList<>();
        for(Account a : accounts)
        {
            if((a.getBalance() >= start) && (a.getBalance() <= end))
            {
                accountMatching.add(a);
            }
        }
        return accountMatching;
    }

    public List<Account> FilterOnUserIdAccounts(Long userId) {

        List<Account> accountMatchingUserId = new ArrayList<>();
        for(Account a : accounts)
        {
            if(a.getUserId().equals(userId))
            {
                accountMatchingUserId.add(a);
            }
        }
        return accountMatchingUserId;
    }
}
