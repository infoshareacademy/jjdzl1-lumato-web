package com.infoshare.lumato.test;

import com.infoshare.lumato.models.UserBean;
import com.infoshare.lumato.models.UserController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/lumato?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "password";

        try {

            List<UserBean> list = new UserController().getUsers();
            for (UserBean userBean :list) {
                System.out.println(userBean.toString());

            }
        } catch (Exception e){
            System.out.println("Erorr!");
            e.printStackTrace();
        }
    }
}
