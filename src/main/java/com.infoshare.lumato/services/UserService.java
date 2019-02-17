package com.infoshare.lumato.services;

import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.models.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
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

}