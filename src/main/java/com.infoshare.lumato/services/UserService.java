package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.UserDAO;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HttpUtils;
import com.infoshare.lumato.utils.SecurityUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@RequestScoped
public class UserService {

    @Inject
    UserDAO userDAO;


    public void addUser(User user) {
        userDAO.addOrUpdateUser(user);
        User justCreatedUser = userDAO.findUserInDatabaseByEmail(user.getEmail());
        storeInSession(justCreatedUser);
    }

    public boolean verifyLoginAttempt(User user) {
        if (user.getEmail() == null || user.getEmail().length() == 0) return false;
        User userInDB = userDAO.findUserInDatabaseByEmail(user.getEmail());
        String attemptedPassword = user.getPassword();
        String storedPassword = userInDB.getPassword();
        boolean passwordMatches = SecurityUtils.validatePassword(attemptedPassword, storedPassword);
        if (userInDB == null) return false;
        if (passwordMatches) {
            fillUserData(user, userInDB);
            return true;
        }
        return false;
    }

    public void fillUserData(User userToFill, User fullUserFiller) {
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
        User currentUser = HttpUtils.getCurrentUserFromSession();
        fillUserData(user, currentUser);
        userDAO.addOrUpdateUser(user);
        currentUser.setFirstName(user.getFirstName());
        currentUser.setLastName(user.getLastName());
        currentUser.setEmail(user.getEmail());
        currentUser.setPassword(user.getPassword());
    }

    public boolean passwordMatchesUserInSessionPassword(User user) {
        User currentUser = HttpUtils.getCurrentUserFromSession();
        String attemptedPassword = user.getPassword();
        String storedPassword = currentUser.getPassword();
        boolean passwordMatches = SecurityUtils.validatePassword(attemptedPassword, storedPassword);
        return passwordMatches;
    }

    public void deleteUser(User user){
        userDAO.deleteUser(user.getUserId());
    }
}