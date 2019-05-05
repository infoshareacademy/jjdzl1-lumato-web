package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.dao.TokenDao;
import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.UserService;
import com.infoshare.lumato.utils.SecurityUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.util.Map;

@RequestScoped
@Named("loginBean")
public class LoginViewBean {

    @Inject
    TokenDao tokenDao;

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
            String rawPassword = user.getPassword();
            String passwordHashed = SecurityUtils.generatePasswordHash(rawPassword);
            user.setPassword(passwordHashed);
            userService.storeInSession(user);
            Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
            String userToken = tokenDao.generateUserToken(user);
            sessionMap.put("userid", user.getUserId());
            sessionMap.put("usertoken", userToken);
            HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "app/start.xhtml");
        } else {
            messageService.addMessageCookie("wrongCredentialsMessage", "Incorrect email or password!");
            HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "login.xhtml");
        }
    }

    public void logOut() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().invalidateSession();
        try {
            context.getExternalContext().redirect(HttpUtils.getRequest().getContextPath() + "login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
