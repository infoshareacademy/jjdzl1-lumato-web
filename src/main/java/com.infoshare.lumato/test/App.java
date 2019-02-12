package com.infoshare.lumato.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/lumato?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "password";

        try {
            // 1. Get a connection to database.
            Connection myConnection = DriverManager.getConnection(url, user, password);

            // 2. Create a statement
            Statement myStatement = myConnection.createStatement();

            // 3. Execute SQL query
            ResultSet resultSet = myStatement.executeQuery("select * from users");

            // 4. Process the results
            Displayer.display(resultSet);

        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }
}
