package com.infoshare.lumato.users;

import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
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

    void loadUsers() {
        try {
            users = userDB.getAllUsers();

        } catch (Exception e) {
            System.out.println("Cannot load users!");
            e.printStackTrace();
        }
    }

    public String addUser(UserBean theUser) {
        try {
            userDB.addUser(theUser);

        } catch (Exception exc) {
            exc.printStackTrace();
            System.out.println("Failed to add user!");

        }
        return "start.xhtml";
    }

}


















