package com.infoshare.lumato.config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DataBaseProp {


    public String getUrl() {
        return "jdbc:mysql://127.0.0.1:3306/lumato?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    }

    public String getPassword() {
        return "password";
    }

    public String getUser() {
        return "root";
    }

}
