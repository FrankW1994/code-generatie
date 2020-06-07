package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

class TransactionTest {

    private Transaction transaction;

    @BeforeEach
    public void Setup() {
        transaction = new Transaction();
    }

    @Test
    public void createTransactionShouldNotBeNull() {
        assertNotNull(transaction);
    }

    @Test
    public void settingAmountBelowZeroShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> transaction.setTransferAmount(-1D));
        assertEquals("Amount cannot be below zero", exception.getMessage());
    }

    @Test
    public void iBanMustMatchFormat()
    {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> transaction.setIbanReceiver("abababbaba"));
        assertEquals("IBAN MUST BE TYPE OF NLXXINHOXXXXXXXX", exception.getMessage());
    }
}