package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.User;
import com.infoshare.lumato.persistence.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@RequestScoped
public class UserDAO {

    @Inject
    DBConnection myConn;

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
}
