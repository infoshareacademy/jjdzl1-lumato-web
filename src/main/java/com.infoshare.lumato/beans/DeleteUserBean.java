package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.User;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("deleteUser")
public class DeleteUserBean {

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

    public void deleteUser() {
        if (userService.passwordIsOk(user)) {
            userService.deleteUser(HttpUtils.getCurrentUserFromSession().getUserId());
            HttpUtils.redirect("/login.xhtml");
        } else {
            messageService.addWrongPasswordMessage();
            user = null;
            HttpUtils.redirect("/app/user-management.xhtml");
        }
    }
}
