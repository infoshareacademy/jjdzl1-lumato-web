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
@Named("editUser")
public class ChangeUserDataBean {

    private String newPasswordFirst;
    private String newPasswordSecond;

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

    public String getNewPasswordFirst() {
        return newPasswordFirst;
    }

    public void setNewPasswordFirst(String newPasswordFirst) {
        this.newPasswordFirst = newPasswordFirst;
    }

    public String getNewPasswordSecond() {
        return newPasswordSecond;
    }

    public void setNewPasswordSecond(String newPasswordSecond) {
        this.newPasswordSecond = newPasswordSecond;
    }

    public void updateUser() {
        if (user.getEmail() != null) {
            if (userService.doesUserExist(user)) {
                messageService.addUserAlreadyExistMessage();
                HttpUtils.redirect("/app/user-management.xhtml");
                return;
            }
        }

        if (user.getPassword() != null) {
            if (!userService.passwordIsOk(user)) {
                messageService.addWrongPasswordMessage();
                HttpUtils.redirect("/app/user-management.xhtml");
                return;
            }
        }

        if (this.newPasswordFirst != null && this.newPasswordSecond != null){
            if (!this.newPasswordFirst.equals(this.newPasswordSecond)) {
                messageService.addPasswordsDoNotMatchMessage();
                HttpUtils.redirect("/app/user-management.xhtml");
                return;
            } else {
                user.setPassword(this.newPasswordFirst);
            }
        }

        userService.updateUser(user);
        user = null;
    }
}
