package com.infoshare.lumato;

import com.infoshare.lumato.models.User;
import com.infoshare.lumato.services.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class UserController {

    private List<User> users = new ArrayList<>();

    @Inject
    private UserService userDB;

    public UserController() throws Exception {
        users = new ArrayList<>();
    }

    public List<User> getUsers() {
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

    public String addUser(User theUser) {
        try {
            userDB.addUser(theUser);

        } catch (Exception exc) {
            exc.printStackTrace();
            System.out.println("Failed to add user!");

        }
        return "start.xhtml";
    }
}


















