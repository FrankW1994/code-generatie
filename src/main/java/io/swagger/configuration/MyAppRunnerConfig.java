package io.swagger.configuration;

import io.swagger.dao.*;
import io.swagger.model.*;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.*;

import static io.swagger.model.Account.RankEnum.CURRENT;
import static io.swagger.model.Account.RankEnum.SAVING;
import static io.swagger.model.Account.StatusEnum.ACTIVE;
import static io.swagger.model.Account.StatusEnum.BLOCKED;

@Component
//@ConditionalOnProperty(prefix = "guitarshop.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MyAppRunnerConfig implements ApplicationRunner {

    private RepositoryAccount repositoryAccount;
    private RepositoryTransaction repositoryTransaction;
    private RepositoryUser repositoryUser;
    private PropertyConfig properties;
    private RepositoryApiKey apiKeyRepository;
    private RepositoryLogin repositoryLogin;

    public MyAppRunnerConfig(RepositoryAccount accountRepository, RepositoryTransaction repositoryTransaction, RepositoryUser repositoryUser, PropertyConfig properties, RepositoryApiKey apiKeyRepository, RepositoryLogin repositoryLogin) {
        this.repositoryAccount = accountRepository;
        this.repositoryTransaction = repositoryTransaction;
        this.repositoryUser = repositoryUser;
        this.properties = properties;
        this.apiKeyRepository = apiKeyRepository;
        this.repositoryLogin = repositoryLogin;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Account> accounts = new ArrayList<>(
                Arrays.asList(
                        new Account(1L, "NL77INHO0123456789", CURRENT, ACTIVE, 1660.00D, "EUR"),
                        new Account(2L, "NL22INHO9876543210", SAVING, ACTIVE, 5504.00D, "EUR"),
                        new Account(3L, "NL33INHO3333333333", CURRENT, BLOCKED, 604.00D, "EUR"),
                        new Account(14L, "NL11INHO1111111111", CURRENT, ACTIVE, 1700.00D, "EUR")
                )
        );

        accounts.forEach(repositoryAccount::save);

        List<Login> logins = new ArrayList<>(
                Arrays.asList(
                        new Login("test", "test")
                )
        );
        logins.forEach(repositoryLogin::save);

        List<ApiKey> apiKeys = new ArrayList<>(
                Arrays.asList(
                        new ApiKey(1L,"100001", "bill")
                )
        );
        apiKeys.forEach(apiKeyRepository::save);

        repositoryLogin.findAll().forEach(System.out::println);

        List<Transaction> transactions = new ArrayList<>(
                Arrays.asList(
                        new Transaction("NL77INHO0123456789", "NL11INHO1111111111", "GPOSSEL", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()), 140D),
                        new Transaction("NL22INHO9876543210", "NL33INHO3333333333", "SBOERE", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()), 9D),
                        new Transaction("NL11INHO1111111111", "NL22INHO9876543210", "TWUBBEN", new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()), 100D)
                )
        );

        transactions.forEach(repositoryTransaction::save);

        repositoryTransaction.FindTransactionsOver(100).forEach(System.out::println);

        List<User> users =
                Arrays.asList(
                        new User("Bill", "Nye", "billnye@email.com", "test", "0612345678", "1990-11-20", "20-10-2019", User.RankEnum.EMPLOYEE, User.StatusEnum.ACTIVE),
                        new User("Henk", "Anders", "henkanders@email.com", "test","0687654321", "1994-2-23", "05-02-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE),
                        new User("Klaas", "Vaak", "klaasvaak@email.com", "test", "0600112233", "2000-1-21", "10-03-2020", User.RankEnum.CUSTOMER, User.StatusEnum.ACTIVE));

        users.forEach(repositoryUser::save);

        System.out.println("Application name: " + properties.getApplicationName());
    /*
    Generate random API Keys
    */
        for (int i = 0; i < 5; i++) {
            UUID uuid = UUID.randomUUID();
            apiKeyRepository.save(new ApiKey(uuid.toString()));
        }
    }

}

