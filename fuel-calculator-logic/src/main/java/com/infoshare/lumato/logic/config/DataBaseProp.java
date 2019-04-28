package com.infoshare.lumato.logic.config;


import com.infoshare.lumato.logic.utils.HttpUtils;

import javax.servlet.ServletContext;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseProp {

    //Tomek settings
//    private String url = "jdbc:mysql://127.0.0.1:3306/lumato?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    private String password = "password";
//    private String user = "root";

    //Łukasz settings
//    private String url =  "jdbc:mysql://192.168.99.100:9001/lumato";
//    private String password = "root";
//    private String user = "root";

    private Properties getProperties(){
        ServletContext context = HttpUtils.getRequest().getServletContext();
        String fullPath = context.getRealPath("/config/db.properties");
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(fullPath));
        } catch (IOException e) {
            System.out.println("NO db.properties FILE in /webapp/config/ OR WRONG db.properties CONTENT");
            e.printStackTrace();
        }
        return properties;
    }

    public String getUrl() {
        return getProperties().getProperty("url");
    }

    public String getPassword() {
        return getProperties().getProperty("password");
    }

    public String getUser() {
        return getProperties().getProperty("user");
    }
}
