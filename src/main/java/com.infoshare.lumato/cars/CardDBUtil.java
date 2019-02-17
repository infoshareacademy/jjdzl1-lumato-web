package com.infoshare.lumato.cars;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardDBUtil {

    private static String driverName = "com.mysql.cj.jdbc.Driver";
    private static Connection myConnection;

    private List<CarsBean> cars = new ArrayList<>();


}
