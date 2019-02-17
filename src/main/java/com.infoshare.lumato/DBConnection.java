package com.infoshare.lumato;



import javax.ejb.Stateless;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


@Stateless
public class DBConnection {

    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static Connection myConnection;


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
}
