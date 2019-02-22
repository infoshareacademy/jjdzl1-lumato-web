package com.infoshare.lumato.services;

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

    private List<User> users = new ArrayList<>();

    @Inject
    DBConnection myConn;

    public List<User> getAllUsers() {
        try {
            Statement myStatement = myConn.getConnection().createStatement();
            ResultSet resultSet = myStatement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                int iduser = resultSet.getInt("iduser");
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");
                User tempUser = new User(iduser, firstName, lastName, email);
                users.add(tempUser);
            }
        } catch (SQLException e) {
            System.out.println("Failed to create a connection");
            e.printStackTrace();
        }
        System.out.println("Driver not found.");
        return users;
    }

    public void addUser(User theUser) {
        try {
            String sql = "insert into users (firstname, lastname, email, password) values (?, ?, ?, ?)";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);

            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPassword());

            myStmt.execute();

        } catch (Exception ecx) {
            ecx.printStackTrace();
            System.out.println("Failed to Add new User!");
        }
    }

    public User findUserInDatabaseByEmail(String email) {
        User userInDB = new User();
        try {
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement statement = myConn.getConnection().prepareStatement(sql);
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                userInDB = null;
            } else {
                resultSet.next();
                userInDB.setEmail(resultSet.getString("email"));
                userInDB.setPassword(resultSet.getString("password"));
                userInDB.setFirstName(resultSet.getString("firstname"));
                userInDB.setLastName(resultSet.getString("lastname"));
                userInDB.setUserId(resultSet.getInt("iduser"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userInDB;
    }

    void updateUser(User theUser) {
        try {
            String sql = "update users set first_name=?, last_name=?, email=? where id=?";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);

            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getEmail());

            myStmt.execute();

        } catch (Exception exc) {
            System.out.println("Cannot update an user!");
            exc.printStackTrace();
        }
    }

    public boolean verifyLoginAttempt(User user) {
        User userInDB = findUserInDatabaseByEmail(user.getEmail());
        if (userInDB == null) return false;
        if (userInDB.getPassword().equals(user.getPassword())) {
            fillUserData(user, userInDB);
            return true;
        }
        return false;
    }

    private void fillUserData(User userToFill, User userInDB) {
        userToFill.setUserId(userInDB.getUserId());
        userToFill.setFirstName(userInDB.getFirstName());
        userToFill.setLastName(userInDB.getLastName());
    }

    public void storeInSession(User user) {
        HttpSession session = HttpUtils.getSession();
        session.setAttribute("currentUser", user);
    }

    public boolean doesUserExist(User user) {
        User userInDB = findUserInDatabaseByEmail(user.getEmail());
        if (userInDB == null) return false;
        return (userInDB.getEmail().equals(user.getEmail()));
    }
}