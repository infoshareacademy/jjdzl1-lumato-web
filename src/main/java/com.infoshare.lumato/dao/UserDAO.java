package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.utils.HibernateConfig;
import org.hibernate.HibernateError;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UserDAO extends CommonDAO {

    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    public List<User> getAllUsers() {

        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();

        Query<User> query =
                currentSession.createQuery("FROM User", User.class);

        List<User> users = query.getResultList();

        currentSession.getTransaction().commit();
        currentSession.close();
        return users;
    }

    public void addOrUpdateUser(User theUser) {

        Session currentSession = sessionFactory.openSession();

        currentSession.beginTransaction();

        currentSession.saveOrUpdate(theUser);

        currentSession.getTransaction().commit();
        currentSession.close();
    }

    // TODO: 07.04.2019 optional?
    public User findUserInDatabaseByEmail(String email) {
        User userInDB = null;

        Session currentSession = sessionFactory.openSession();

        currentSession.beginTransaction();

        try {
            String hQuery = "FROM User U WHERE U.email=:theEmail";
            userInDB = currentSession.createQuery(hQuery, User.class).setParameter("theEmail", email).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("No user in DataBAse!@");
        }

        currentSession.getTransaction().commit();
        currentSession.close();
        return userInDB;
    }

    public void deleteCurrentUser(User theUser) {

        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();

        User tempUser = currentSession.get(User.class, theUser.getUserId());

        currentSession.delete(tempUser);


        currentSession.getTransaction().commit();
        currentSession.close();
    }
}
