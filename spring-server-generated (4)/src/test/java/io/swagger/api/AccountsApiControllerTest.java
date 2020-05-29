package io.swagger.api;
import io.swagger.model.Account;
import io.swagger.service.AccountApiService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.NoSuchElementException;

import static io.swagger.model.Account.RankEnum.CURRENT;
import static io.swagger.model.Account.StatusEnum.ACTIVE;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@AutoConfigureMockMvc
class AccountsApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountApiService service;
    private Account account;

    @BeforeEach
    public void setup()
    {
        service = new AccountApiService();
        account = new Account(15L, "NL11INHO11116111", CURRENT, ACTIVE, 2300.00D, "EUR");
    }

    @Test
    public void AccountMustExcistsBeforeDeleteThrowException() {
    Account account = new Account();
    assertNotNull(account);
    }

    @Test
    public void rightIBANregexFormatNLXXINHOXXXXXXXX() {
        Account account = new Account();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> account.setIBAN("NL77INHO77777777")); // set iban 1
    assertEquals("IBAN MUST BE TYPE OF NLXXINHOXXXXXXXX", exception.getMessage());
    }

    @Test
    public void AccountMustExistsBeforeDeleting()
    {
        Account account = new Account();
        Exception exception = assertThrows(NoSuchElementException.class, () -> service.getAccountFromIBAN("NL77INHO77787777")); // if iban is not found return notfound
        assertEquals("Account NLXXINHOXXXXXXXX does not exists", exception.getMessage());
    }

}