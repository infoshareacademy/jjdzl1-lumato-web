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

    User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

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

    void sendUpdateUserQuery(User user) {
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

    public boolean verifyLoginAttempt(User user) {
        User userInDB = findUserInDatabaseByEmail(user.getEmail());
        if (userInDB == null) return false;
        if (userInDB.getPassword().equals(user.getPassword())) {
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
        User userInDB = findUserInDatabaseByEmail(user.getEmail());
        if (userInDB == null) return false;
        return (userInDB.getEmail().equals(user.getEmail()));
    }

    public void updateUser(User user) {
        fillUserData(user, this.currentUser);
        sendUpdateUserQuery(user);
        this.currentUser.setFirstName(user.getFirstName());
        this.currentUser.setLastName(user.getLastName());
        this.currentUser.setEmail(user.getEmail());
        this.currentUser.setPassword(user.getPassword());
    }

    public boolean passwordIsOk(User user) {
        return user.getPassword().equals(this.currentUser.getPassword());
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