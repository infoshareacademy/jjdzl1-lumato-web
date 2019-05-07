package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.User;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;

@Named
@RequestScoped
public class UserDAO extends CommonDAO {

    public void addOrUpdateUser(User theUser) {
        Session currentSession = this.getSession();
        currentSession.saveOrUpdate(theUser);
        executeAndCloseTransaction(currentSession);
    }

    public User findUserInDatabaseByEmail(String email) {
        User userInDB = null;
        Session currentSession = this.getSession();

        try {
            String hQuery = "FROM User U WHERE U.email=:theEmail";
            userInDB =
                    currentSession.createQuery(hQuery, User.class).setParameter("theEmail", email).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user in DataBAse!@");
        }

        executeAndCloseTransaction(currentSession);
        return userInDB;
    }

    //delete
}
