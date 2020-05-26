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
    {}


    public Account getAccountFromIBAN(String ibanReceiver) {

            return repositoryAccount.findOne(ibanReceiver);
        //    throw new NoSuchElementException("Account NLXXINHOXXXXXXXX does not exists");

    }

  //  public void updateNewBalanceServiceAccounts(Account receiver, Account sender) {

        /// UPDATE in databse (DATAbase not yet available use list
   //     for(Account a : accounts)
   //     {
    //        if(a.getIBAN().equals(receiver.getIBAN()))
    //        {
    //            a = receiver;
   //         }
    //        if(a.getIBAN().equals(sender.getIBAN()))
    //        {
    //            a = sender;
   //         }
    //    }
   // }

    public List<Account> getAllAccounts() {
            return (List<Account>) repositoryAccount.findAll();
            //throw new IllegalStateException("List is empty..");
    }

//
//    public List<Account> FilterOnBalanceAccounts(Double balance) {
//        Double start = balance * 0.90;
//        Double end = balance * 1.10;
//        List<Account> accountMatching = new ArrayList<>();
//        for(Account a : accounts)
//        {
//            if((a.getBalance() >= start) && (a.getBalance() <= end))
//            {
//                accountMatching.add(a);
//            }
//        }
//        return accountMatching;
//    }
//
//    public List<Account> FilterOnUserIdAccounts(Long userId) {
//
//        List<Account> accountMatchingUserId = new ArrayList<>();
//        for(Account a : accounts)
//        {
//            if(a.getUserId().equals(userId))
//            {
//                accountMatchingUserId.add(a);
//            }
//        }
//        return accountMatchingUserId;
//    }
}
