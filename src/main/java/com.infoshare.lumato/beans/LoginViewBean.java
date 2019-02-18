package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.User;
import com.infoshare.lumato.services.UserService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("loginBean")
public class LoginViewBean {

    @Inject
    private UserService userService;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PostConstruct
    public void construct() {
        user = new User();
    }

    public String attemptToLogIn() {
        if (userService.verifyLoginAttempt(user)) {
            userService.storeInSession(user);
            userService.deleteErrorMessagesFromSession();
            return "start.xhtml";
        } else {
            userService.addWrongCredentialsMessageToSession();
            return "login.xhtml";
        }
    }
}
