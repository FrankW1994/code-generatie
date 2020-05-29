package io.swagger.configuration;

import io.swagger.dao.RepositoryAccount;
import io.swagger.dao.RepositoryApiKey;
import io.swagger.dao.RepositoryTransaction;
import io.swagger.model.Account;
import io.swagger.model.ApiKey;
import io.swagger.model.Transaction;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.*;

import static io.swagger.model.Account.RankEnum.CURRENT;
import static io.swagger.model.Account.RankEnum.SAVING;
import static io.swagger.model.Account.StatusEnum.ACTIVE;
import static io.swagger.model.Account.StatusEnum.BLOCKED;

@Component
@ConditionalOnProperty(prefix = "guitarshop.autorun", name = "enabled", havingValue = "true", matchIfMissing = true)
public class MyAppRunnerConfig implements ApplicationRunner {

    private RepositoryAccount repositoryAccount;
    private RepositoryTransaction repositoryTransaction;
    private PropertyConfig properties;
    private RepositoryApiKey apiKeyRepository;

    public MyAppRunnerConfig(RepositoryAccount accountRepository, RepositoryTransaction repositoryTransaction, PropertyConfig properties, RepositoryApiKey apiKeyRepository) {
        this.repositoryAccount = accountRepository;
        this.repositoryTransaction = repositoryTransaction;
        this.properties = properties;
        this.apiKeyRepository = apiKeyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<Account> accounts = new ArrayList<>(
                Arrays.asList(
                        new Account(1L, "NL77INHO77777777", CURRENT, ACTIVE, 1660.00D, "EUR"),
                        new Account(2L, "NL22INHO222222", SAVING, ACTIVE, 5504.00D, "EUR"),
                        new Account(3L, "NL33INHO33333333", CURRENT, BLOCKED, 604.00D, "EUR"),
                        new Account(14L, "NL11INHO11111111", CURRENT, ACTIVE, 1700.00D, "EUR")
                )
        );

        accounts.forEach(repositoryAccount::save);

        List<Transaction> transactions = new ArrayList<>(
                Arrays.asList(
                        new Transaction("NL11INHO11111111", "NL22INHO222222", "GPOSSEL", "03/04/2020", 140D),
                        new Transaction("NL77INHO77777777", "NL22INHO222222", "SBOERE", "03/04/2020", 9D),
                        new Transaction("NL33INHO33333333", "NL44INHO444444", "TWUBBEN", "01/01/2020", 100D)
                )
        );

        transactions.forEach(repositoryTransaction::save);
        //       repositoryAccount.findAll().forEach(System.out::println);
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

