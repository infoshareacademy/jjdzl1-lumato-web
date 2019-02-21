package com.infoshare.lumato.persistence;


import com.infoshare.lumato.config.DataBaseProp;

import javax.ejb.Stateless;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Stateless
public class DBConnection {

    private static Connection myConnection;

    public Connection getConnection() {

        try {
            DataBaseProp dataBaseProp = new DataBaseProp();
            String password = dataBaseProp.getPassword();
            String url = dataBaseProp.getUrl();
            String user = dataBaseProp.getUser();

            myConnection = DriverManager.getConnection(url, user, password);

        } catch (SQLException e) {
            System.out.println("Failed to create a connection");
            e.printStackTrace();
        }
        return myConnection;
    }
}
