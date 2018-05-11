package com.lmartino.todolist.service;

import org.junit.Test;

import static org.junit.Assert.*;

public class AuthenticationServiceTest {

    private AuthenticationService authenticationService = new AuthenticationService();

    @Test
    public void canCryptPasswordWithMD5Algorithm() throws Exception {
        String plainText = "password123";

        // encrypt 1 time
        final String firstEncryption = authenticationService.hash(plainText);

        assertNotNull(firstEncryption);
        assertNotEquals(firstEncryption, plainText);

        // encrypt 2 times
        final String secondEncryption = authenticationService.hash(plainText);

        assertNotNull(secondEncryption);
        assertEquals(firstEncryption, secondEncryption);



    }
}