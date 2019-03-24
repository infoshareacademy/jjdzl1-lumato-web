package com.infoshare.lumato.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilsTest {

    String password;
    String passwordHashed;

    @BeforeEach
    void preparePasswordAndHash() throws InvalidKeySpecException, NoSuchAlgorithmException {
        password = "aaa";
        passwordHashed = SecurityUtils.generatePasswordHash(password);
    }

    @Test
    void passwordHashCannotBeNull() {
        assertFalse(passwordHashed == null);
    }

    @Test
    void passwordHashMustBeLongerThanZeroCharacters() {
        assertTrue(passwordHashed.length() > 0);
    }

    @Test
    void passwordHashMustBeLongerThanHundredCharacters() {
        assertTrue(passwordHashed.length() >100);
    }

    @Test
    void validatePasswordShouldReturnTrue() throws InvalidKeySpecException, NoSuchAlgorithmException {
        boolean validation = SecurityUtils.validatePassword(password, passwordHashed);
        assertTrue(validation);
    }
}