package com.infoshare.lumato.services;

import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

@RequestScoped
@Named("messageBean")
public class MessageService {


    public void addWrongCredentialsMessage() {
        Cookie kookey = new Cookie("loginErrorMessage", "Wrong credentials");
        kookey.setMaxAge(5);
        HttpUtils.getResponse().addCookie(kookey);
    }

    public String getWrongCredentialsMessage() {
        return HttpUtils.getCookieValueByCookieName("loginErrorMessage");
    }

    public void addUserAlreadyExistMessage() {
        Cookie kookey = new Cookie("registerErrorMessage", "User already exists!");
        kookey.setMaxAge(5);
        HttpUtils.getResponse().addCookie(kookey);
    }

    public String getUserAlreadyExistMessage(){
        return HttpUtils.getCookieValueByCookieName("registerErrorMessage");
    }
}
