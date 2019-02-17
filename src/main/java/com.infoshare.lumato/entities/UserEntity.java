package com.infoshare.lumato.entities;

import javax.inject.Named;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Named
@Entity(name="users")
@Table(name="users")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 7475275579177057431L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="iduser")
    protected Integer iduser;

    @Column(name="firstname")
    @Size(min=3, max=30)
    protected String firstname;

    @Column(name="lastname")
    @Size(min=3, max=30)
    protected String lastname;

    @Column(name="password")
    @Size(min=3, max=30)
    protected String password;

    @Column(name="email")
    @Email
    protected String email;

    public UserEntity() {
    }

    public UserEntity(String firstname, String lastname, String password, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIduser() {
        return iduser;
    }
}
