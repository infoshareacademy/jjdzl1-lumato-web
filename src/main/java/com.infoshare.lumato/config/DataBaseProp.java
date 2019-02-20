package com.infoshare.lumato.config;

public class DataBaseProp {

    //Tomek settings
//    private String url = "jdbc:mysql://127.0.0.1:3306/lumato?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
//    private String password = "password";
//    private String user = "root";

    //Łukasz settings
    private String url =  "jdbc:mysql://192.168.99.100:9001/lumato";
    private String password = "root";
    private String user = "root";

    public String getUrl() {
        return this.url;
    }

    public String getPassword() {
        return this.password;
    }

    public String getUser() {
        return this.user;
    }

}
