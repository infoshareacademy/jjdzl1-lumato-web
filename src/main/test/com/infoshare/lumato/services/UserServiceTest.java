package com.infoshare.lumato.services;

import com.infoshare.lumato.models.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class UserServiceTest {

    private static UserService userService;
    private User currentUserLoggedIn;

    @BeforeAll
    static void initializeTests(){
        userService = new UserService();
    }

    @BeforeEach
    void initializeUsers(){
        currentUserLoggedIn = new User(1, "firstname", "lastname", "email");
    }

    @Test
    void fillUserDataShouldWorkIfOnlyEmailIsSet() {
        String newEmail = "test@test.com";
        User userWithEmailOnly = new User();
        userWithEmailOnly.setEmail(newEmail);
        User fullUser = currentUserLoggedIn;
        User userThatShouldBeCreated = currentUserLoggedIn;
        userThatShouldBeCreated.setEmail(newEmail);
        this.userService.fillUserData(userWithEmailOnly, fullUser);
        assertEquals(userThatShouldBeCreated, userWithEmailOnly);
    }

    @Test
    void fillUserDataShouldWorkIfOnlyIdIsSet() {
        int newId = 2;
        User userWithIdOnly = new User();
        userWithIdOnly.setUserId(newId);
        User fullUser = currentUserLoggedIn;
        User userThatShouldBeCreated = currentUserLoggedIn;
        userThatShouldBeCreated.setUserId(newId);
        this.userService.fillUserData(userWithIdOnly, fullUser);
        assertEquals(userThatShouldBeCreated, userWithIdOnly);
    }

    @Test
    void fillUserDataShouldWorkIfOnlyFirstNameIsSet() {
        String newFirstName = "firstnametest";
        User userWithFirstNameOnly = new User();
        userWithFirstNameOnly.setFirstName(newFirstName);
        User fullUser = currentUserLoggedIn;
        User userThatShouldBeCreated = currentUserLoggedIn;
        userThatShouldBeCreated.setFirstName(newFirstName);
        this.userService.fillUserData(userWithFirstNameOnly, fullUser);
        assertEquals(userThatShouldBeCreated, userWithFirstNameOnly);
    }

    @Test
    void fillUserDataShouldWorkIfOnlyLastNameIsSet() {
        String newLastName = "lastnametest";
        User userWithLastNameOnly = new User();
        userWithLastNameOnly.setLastName(newLastName);
        User fullUser = currentUserLoggedIn;
        User userThatShouldBeCreated = currentUserLoggedIn;
        userThatShouldBeCreated.setLastName(newLastName);
        this.userService.fillUserData(userWithLastNameOnly, fullUser);
        assertEquals(userThatShouldBeCreated, userWithLastNameOnly);
    }
}