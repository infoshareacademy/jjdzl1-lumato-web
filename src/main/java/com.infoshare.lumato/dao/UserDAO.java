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
        System.out.println("\n\n\n\n ++++++++++++++ getAllUsers Query  ");

        List<User> users = query.getResultList();
        System.out.println("\n\n\n\n ++++++++++++++ getAllUsers ResultList  ");
        System.out.println(users);


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

    public User findUserInDatabaseByEmail(String email) {
       /* User userInDB = new User();
        try {
            String sql = "SELECT * FROM user WHERE email = ?";
            PreparedStatement statement = myConn.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                userInDB = null;
            } else {
                resultSet.next();
                userInDB.setEmail(resultSet.getString("email"));
                userInDB.setPassword(resultSet.getString("password"));
                userInDB.setFirstName(resultSet.getString("first_name"));
                userInDB.setLastName(resultSet.getString("last_name"));
                userInDB.setUserId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInDB;*/

        User userInDB = null;

        Session currentSession = sessionFactory.openSession();

        currentSession.beginTransaction();

        try {
            String hQuery = "FROM User U WHERE U.email=:theEmail";
            userInDB = currentSession.createQuery(hQuery, User.class).setParameter("theEmail", email).getSingleResult();
        } catch (NoResultException e) {
            System.out.println("\n\n\n\n **************************** W NoResultException w TRY");
        }

        currentSession.getTransaction().commit();
        currentSession.close();
        return userInDB;
    }

    public void deleteUser(int userId) {

        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();

        Query query =
                currentSession.createQuery("DELETE from User where id=:userId");
        currentSession.delete(userId);

        query.setParameter("userId", userId).executeUpdate();

        currentSession.getTransaction().commit();
        currentSession.close();
    }
}
