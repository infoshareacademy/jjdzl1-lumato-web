package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.User;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;
import com.infoshare.lumato.utils.SessionUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("loginBean")
public class LoginViewBean {

    @Inject
    private UserService userService;

    @Inject
    private MessageService messageService;

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

    public void attemptToLogIn() {
        if (userService.verifyLoginAttempt(user)) {
            userService.storeInSession(user);
            messageService.deleteWrongCredentialsMessage();
            SessionUtils.redirect("start.xhtml");
        } else {
            messageService.addWrongCredentialsMessage();
            SessionUtils.redirect("login.xhtml");
        }
    }
}
