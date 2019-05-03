package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;

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
        if (userService.passwordMatchesUserInSessionPassword(user)) {
            userService.deleteCurrentUser();
            HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "/login.xhtml");
        } else {
            messageService.addMessageCookie("wrongPassword", "Wrong password!");
            user = null;
            HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "/app/user-delete.xhtml");
        }
    }
}
