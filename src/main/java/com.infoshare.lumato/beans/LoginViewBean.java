package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.User;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;

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
            HttpUtils.redirect("/app/start.xhtml");
        } else {
            messageService.addWrongCredentialsMessage();
            HttpUtils.redirect("/login.xhtml");
        }
    }

    public void logOut() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect("/login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
