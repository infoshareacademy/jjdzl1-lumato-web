package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;
import com.infoshare.lumato.utils.SecurityUtils;

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
            HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "register.xhtml");
        } else {
            String rawPassword = user.getPassword();
            String passwordHashed = SecurityUtils.generatePasswordHash(rawPassword);
            user.setPassword(passwordHashed);
            userService.addUser(user);
            HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "app/start.xhtml");
        }

    }
}
