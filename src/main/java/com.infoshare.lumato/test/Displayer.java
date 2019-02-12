package com.infoshare.lumato.test;

import java.sql.ResultSet;

public class Displayer {

    public static void display(ResultSet resultSet) {

        try {

            while (resultSet.next()) {
                System.out.println(resultSet.getString("lastname")
                        + ", "
                        + resultSet.getString("firstname")
                        + ", "
                        + resultSet.getString("email")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

