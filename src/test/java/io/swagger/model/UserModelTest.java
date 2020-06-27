package io.swagger.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class UserModelTest {

    private User user;

    @BeforeEach
    public void Setup() {
        user = new User();
    }

    @Test
    public void createUserShouldNotBeNull() {
        assertNotNull(user);
    }

    /**
     * WRONG SETTERS
     *
     * Invalid Email Adres
     * Invalid Phone number
     * Invalid Password (doesnt match the requirements)
     * First and Lastname are to long or have integers
     * */
/*
    @Test
    public void settingAmountBelowZeroShouldThrowException() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setTransferAmount(-1D));
        assertEquals("Amount cannot be below zero", exception.getMessage());
    }

    @Test
    public void iBanMustMatchFormat()
    {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> user.setIbanReceiver("abababbaba"));
        assertEquals("IBAN MUST BE TYPE OF NLXXINHOXXXXXXXXXX", exception.getMessage());
    }
*/


}