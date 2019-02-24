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

    public void updateUserFirstName(){
        userService.updateUser(user);
        HttpUtils.redirect("/app/user-management.xhtml");
    }

    public void updateUserLastName(){
        userService.updateUser(user);
        HttpUtils.redirect("/app/user-management.xhtml");
    }

    public void updateUserEmail(){
        if (userService.doesUserExist(user)){
            messageService.addMessageCookie("userAlreadyExists", "Such user already exists!");
            HttpUtils.redirect("/app/user-management.xhtml");
        } else {
            userService.updateUser(user);
            HttpUtils.redirect("/app/user-management.xhtml");
        }
    }

    public void updateUserPassword(){
        if (!userService.passwordIsOk(user)){
            messageService.addMessageCookie("wrongPasswordMessageWhileEdit", "Wrong password!");
            HttpUtils.redirect("/app/user-management.xhtml");
            return;
        }
        if (!this.newPasswordFirst.equals(this.newPasswordSecond)) {
            messageService.addMessageCookie("passwordsNotMatch", "Passwords do not match!");
            HttpUtils.redirect("/app/user-management.xhtml");
            return;
        }
        user.setPassword(newPasswordFirst);
        userService.updateUser(user);
        HttpUtils.redirect("/app/user-management.xhtml");
    }


}
