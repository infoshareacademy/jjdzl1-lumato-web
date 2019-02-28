package com.infoshare.lumato.beans;

import com.infoshare.lumato.dao.UserDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named("startBean")
public class StartViewBean {

    @Inject
    UserDAO userDAO;

    public int countAllUsers(){
        return userDAO.countAllRecords("users");
    }

    public int countAllCars(){
        return userDAO.countAllRecords("cars");
    }

}
