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
        Cookie kookey = new Cookie("registerErrorMessage", "User with such email already exists!");
        kookey.setMaxAge(5);
        HttpUtils.getResponse().addCookie(kookey);
    }

    public String getUserAlreadyExistMessage(){
        return HttpUtils.getCookieValueByCookieName("registerErrorMessage");
    }

    public void addPasswordsDoNotMatchMessage() {
        Cookie kookey = new Cookie("passwordsNotMatchMessage", "Passwords do not match!");
        kookey.setMaxAge(5);
        HttpUtils.getResponse().addCookie(kookey);
    }

    public String getPasswordsDoNotMatchMessage(){
        return HttpUtils.getCookieValueByCookieName("passwordsNotMatchMessage");
    }

    public void addWrongPasswordMessage() {
        Cookie kookey = new Cookie("wrontPasswordMessage", "Wrong password!");
        kookey.setMaxAge(5);
        HttpUtils.getResponse().addCookie(kookey);
    }

    public String getWrongPasswordMessage(){
        return HttpUtils.getCookieValueByCookieName("wrontPasswordMessage");
    }

}
