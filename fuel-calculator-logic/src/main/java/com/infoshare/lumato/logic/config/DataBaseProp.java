package com.infoshare.lumato.logic.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseProp {

    private Properties getProperties(){
        Properties properties = new Properties();
        try {
            File file = new File("config/db.properties");
            System.out.println("FILE PATH: " + file.getAbsolutePath());
            properties.load(new FileInputStream(file));
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
