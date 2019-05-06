package com.infoshare.lumato.logic.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Setter
@Getter
@Entity
@Table(name = "token")
public class Token implements Serializable {

    private static final long serialVersionUID = 8242396670252535134L;

    @Id
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user_token")
    private String userToken;

    public Token() {
    }

    public Token(int userId, String userToken) {
        this.userId = userId;
        this.userToken = userToken;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }
}