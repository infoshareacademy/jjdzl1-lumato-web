package com.infoshare.lumato.models;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UserController {

    private List<UserBean> users = new ArrayList<>();
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

    public void addUser(UserBean theUser) {
        try {
            userDB.addUser(theUser);

        } catch (Exception exc) {
            exc.printStackTrace();
            System.out.println("Failed to add user!");
        }
    }
}
