package com.infoshare.lumato.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SecurityUtilsTest {

    String password;
    String passwordHashed;

    @BeforeEach
    void preparePasswordAndHash() {
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
    void validatePasswordShouldReturnTrue() {
        boolean validation = SecurityUtils.validatePassword(password, passwordHashed);
        assertTrue(validation);
    }
}