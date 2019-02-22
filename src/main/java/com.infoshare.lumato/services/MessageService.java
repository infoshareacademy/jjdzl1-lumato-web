package com.infoshare.lumato.services;

import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.http.Cookie;

@RequestScoped
@Named("messageBean")
public class MessageService {

    public void addMessageCookie(String key, String value) {
        Cookie kookey = new Cookie(key, value);
        kookey.setMaxAge(5);
        HttpUtils.getResponse().addCookie(kookey);
    }

    public String getMessageValue(String key){
        return HttpUtils.getCookieValueByCookieName(key);
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
