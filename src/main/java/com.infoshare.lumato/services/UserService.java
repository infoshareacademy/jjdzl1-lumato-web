package com.infoshare.lumato.services;

import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.SessionUtils;

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
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");

                User tempUser = new User(firstName, lastName, email);
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
            String sql = "insert into users (firstname, lastname, email) values (?, ?, ?)";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);

            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getEmail());

            myStmt.execute();

        } catch (Exception ecx) {
            ecx.printStackTrace();
            System.out.println("Failed to Add new User!");
        }
    }


    /*  Must figure out how we update user  */
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
        if (user.getEmail().equals("admin@admin.pl") && user.getPassword().equals("admin")){
            return true;
        }
        return false;
    }

    public void storeInSession(User user) {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("currentUser", user);
    }

    public void addWrongCredentialsMessageToSession() {
        HttpSession session = SessionUtils.getSession();
        session.setAttribute("loginErrorMessage", "Wrong credentials!");
    }

    public void deleteErrorMessagesFromSession() {
        HttpSession session = SessionUtils.getSession();
        session.removeAttribute("loginErrorMessage");
    }
}