package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.User;
import com.infoshare.lumato.persistence.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
public class UserDAO {

    @Inject
    DBConnection myConn;

    private List<User> users = new ArrayList<>();

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

    public void sendUpdateUserQuery(User user) {
        try {
            String sql = "update users set firstname=?, lastname=?, email=?, password=? where iduser=?";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setString(1, user.getFirstName());
            myStmt.setString(2, user.getLastName());
            myStmt.setString(3, user.getEmail());
            myStmt.setString(4, user.getPassword());
            myStmt.setInt(5, user.getUserId());
            myStmt.executeUpdate();
        } catch (Exception exc) {
            System.out.println("Cannot update an user!");
            exc.printStackTrace();
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

    public void deleteUser(int userId) {
        try {
            String sql = "DELETE FROM users WHERE iduser=?";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, userId);
            myStmt.executeUpdate();
        } catch (Exception exc) {
            System.out.println("Cannot update an user!");
            exc.printStackTrace();
        }
    }
}
