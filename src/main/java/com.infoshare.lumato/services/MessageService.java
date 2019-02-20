package com.infoshare.lumato.services;

import com.infoshare.lumato.utils.SessionUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@RequestScoped
@Named("messageBean")
public class MessageService {

    @Inject
    HttpSession session;

    public void addWrongCredentialsMessage() {
        Cookie kookey = new Cookie("loginErrorMessage", "Wrong credentials");
        kookey.setMaxAge(5);
        SessionUtils.getResponse().addCookie(kookey);
    }

    public String getWrongCredentialsMessage() {
        return SessionUtils.getCookieValueByCookieName("loginErrorMessage");
    }

    public void addUserAlreadyExistMessage() {
        Cookie kookey = new Cookie("registerErrorMessage", "User already exist");
        kookey.setMaxAge(5);
        SessionUtils.getResponse().addCookie(kookey);
    }

    public String getUserAlreadyExistMessage(){
        return SessionUtils.getCookieValueByCookieName("registerErrorMessage");
    }
}
