package com.infoshare.lumato.users;

import com.infoshare.lumato.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
public class UserDBUtil {

    private List<UserBean> users = new ArrayList<>();

    @Inject
    DBConnection myConn;

    List<UserBean> getAllUsers() {
        try {

            Statement myStatement = myConn.getConnection().createStatement();

            ResultSet resultSet = myStatement.executeQuery("SELECT * FROM users");

            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                String lastName = resultSet.getString("lastname");
                String email = resultSet.getString("email");

                UserBean tempUser = new UserBean(firstName, lastName, email);
                users.add(tempUser);
            }

        } catch (SQLException e) {
            System.out.println("Failed to create a connection");
            e.printStackTrace();
        }

        System.out.println("Driver not found.");
        return users;
    }

    void addUser(UserBean theUser) {

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
    void updateUser(UserBean theUser) {

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