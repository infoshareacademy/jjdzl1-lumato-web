package com.infoshare.lumato.services;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@RequestScoped
@Named("messageBean")
public class MessageService {

    @Inject
    HttpSession session;

    // TODO: 2019-02-18 MAKE IT USE COOKIES INSTEAD OF SESSION
    public void addWrongCredentialsMessage() {
//        HttpSession session = SessionUtils.getSession();
        session.setAttribute("loginErrorMessage", "Wrong credentials!");

    }

    // TODO: 2019-02-18 MAKE IT US COOKIES INSTEAD OF SESSION
    public void deleteWrongCredentialsMessage() {
//        HttpSession session = SessionUtils.getSession();
        session.removeAttribute("loginErrorMessage");
    }

    public String getWrongCredentialsMessage(){
//        HttpSession session = SessionUtils.getSession();
        return (String) session.getAttribute("loginErrorMessage");
    }

}
