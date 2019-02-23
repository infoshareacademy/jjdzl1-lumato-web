package com.infoshare.lumato.beans;

import com.infoshare.lumato.dao.UserDAO;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("registerBean")
public class RegisterViewBean {

    @Inject
    private UserService userService;

    @Inject
    UserDAO userDAO;

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

    public void attemptToRegister() {
        if (userService.doesUserExist(user)) {
            messageService.addMessageCookie("userAlreadyExists", "Such user already exists!");
            HttpUtils.redirect("/register.xhtml");
        } else {
            userService.addUser(user);
            HttpUtils.redirect("/app/start.xhtml");
        }

    }


}
