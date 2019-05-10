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
        kookey.setMaxAge(2);
        HttpUtils.getResponse().addCookie(kookey);
    }

    public String getMessageValue(String key){
        return HttpUtils.getCookieValueByCookieName(key);
    }
}
