package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.utils.SecurityUtils;

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

    public void updateUserFirstName(){
        userService.updateUser(user);
        messageService.addMessageCookie("successfulAction", "First name changed with success!");
        HttpUtils.redirect("/app/user-edit.xhtml");
    }

    public void updateUserLastName(){
        userService.updateUser(user);
        messageService.addMessageCookie("successfulAction", "Last name changed with success!");
        HttpUtils.redirect("/app/user-edit.xhtml");
    }

    public void updateUserEmail(){
        if (userService.doesUserExist(user)){
            messageService.addMessageCookie("userExists", "Such user already exists!");
            HttpUtils.redirect("/app/user-edit.xhtml");
        } else {
            userService.updateUser(user);
            messageService.addMessageCookie("successfulAction", "Email changed with success!");
            HttpUtils.redirect("/app/user-edit.xhtml");
        }
    }

    public void updateUserPassword(){
        if (!userService.passwordMatchesUserInSessionPassword(user)){
            messageService.addMessageCookie("wrongPassword", "Wrong password!");
            HttpUtils.redirect("/app/user-change-password.xhtml");
            return;
        }
        if (!this.newPasswordFirst.equals(this.newPasswordSecond)) {
            messageService.addMessageCookie("passwordsNotMatch", "Passwords do not match!");
            HttpUtils.redirect("/app/user-change-password.xhtml");
            return;
        }
        String newPasswordHashed = SecurityUtils.generatePasswordHash(newPasswordFirst);
        user.setPassword(newPasswordHashed);
        messageService.addMessageCookie("successfulAction", "Password changed with success!");
        userService.updateUser(user);
        HttpUtils.redirect("/app/user-change-password.xhtml");
    }


}
