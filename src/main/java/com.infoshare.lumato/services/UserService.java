package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.UserDAO;
import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class UserService {

    @Inject
    DBConnection myConn;

    @Inject
    UserDAO userDAO;


    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    public void addUser(User user) {
        userDAO.addUser(user);
        User justCreatedUser = userDAO.findUserInDatabaseByEmail(user.getEmail());
        storeInSession(justCreatedUser);
    }

    public boolean verifyLoginAttempt(User user) {
        User userInDB = userDAO.findUserInDatabaseByEmail(user.getEmail());
        if (userInDB == null) return false;
        if (userInDB.getPassword().equals(user.getPassword())) {
            fillUserData(user, userInDB);
            return true;
        }
        return false;
    }

    private void fillUserData(User userToFill, User fullUserFiller) {
        if (userToFill.getFirstName() == null) userToFill.setFirstName(fullUserFiller.getFirstName());
        if (userToFill.getLastName() == null) userToFill.setLastName(fullUserFiller.getLastName());
        if (userToFill.getEmail() == null) userToFill.setEmail(fullUserFiller.getEmail());
        if (userToFill.getPassword() == null) userToFill.setPassword(fullUserFiller.getPassword());
        userToFill.setUserId(fullUserFiller.getUserId());
    }

    public void storeInSession(User user) {
        HttpSession session = HttpUtils.getSession();
        session.setAttribute("currentUser", user);
    }

    public boolean doesUserExist(User user) {
        User userInDB = userDAO.findUserInDatabaseByEmail(user.getEmail());
        if (userInDB == null) return false;
        return (userInDB.getEmail().equals(user.getEmail()));
    }

    public void updateUser(User user) {
        fillUserData(user, this.currentUser);
        userDAO.sendUpdateUserQuery(user);
        this.currentUser.setFirstName(user.getFirstName());
        this.currentUser.setLastName(user.getLastName());
        this.currentUser.setEmail(user.getEmail());
        this.currentUser.setPassword(user.getPassword());
    }

    public boolean passwordIsOk(User user) {
        return user.getPassword().equals(this.currentUser.getPassword());
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user.getUserId());
    }
}