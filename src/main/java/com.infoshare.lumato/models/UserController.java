package com.infoshare.lumato.models;

import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
public class UserController {

    private List<UserBean> users;
    private UserDBUtil userDB = new UserDBUtil();

    public UserController() throws Exception {
        users = new ArrayList<>();
    }

    public List<UserBean> getUsers() {
        loadUsers();
        return users;
    }

    public void loadUsers() {
        try {
            users = userDB.getUsers();

        } catch (Exception e) {
            System.out.println("Cannot load users!");
            e.printStackTrace();
        }

    }

}
