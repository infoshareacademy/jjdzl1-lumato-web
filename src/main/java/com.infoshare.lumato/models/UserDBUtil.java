package com.infoshare.lumato.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDBUtil {

    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static Connection myConnection;

    private List<UserBean> users = new ArrayList<>();

    /*
    private static UserDBUtil instance;

    public static UserDBUtil getInstance() throws Exception {
        if (instance == null) {
            instance = new UserDBUtil();
        }
        return instance;
    }*/

    public Connection getConnection() {
        try {
            Class.forName(driverName);
            try {
                String url = "jdbc:mysql://127.0.0.1:3306/lumato?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String password = "password";
                String user = "root";

                myConnection = DriverManager.getConnection(url, user, password);

            } catch (SQLException e) {
                System.out.println("Failed to create a connection");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException exc) {
            System.out.println("Driver not found.");
        }
        return myConnection;
    }


    public List<UserBean> getUsers() {
        try {
            Class.forName(driverName);
            try {
                Statement myStatement = getConnection().createStatement();

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
        } catch (ClassNotFoundException exc) {

            System.out.println("Driver not found.");
        }
        return users;
    }
}

        /*
        }*/

