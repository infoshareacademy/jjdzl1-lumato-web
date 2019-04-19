package com.infoshare.lumato.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Setter
@Getter
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "confirm_password")
    private String confirmPassword;

    @OneToMany(mappedBy = "theUser",
            cascade = {CascadeType.ALL})
    private List<Car> cars;

    @OneToMany(mappedBy = "theUser",
            cascade = {CascadeType.ALL})
    private List<ExtraCosts> extraCostsList;

    @OneToMany(mappedBy = "theUser",
            cascade = {CascadeType.ALL})
    private List<FuelCosts> fuelCostsList;

    public User() {
    }

    public User(int userId, String firstName, String lastName, String email) {
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(confirmPassword, user.confirmPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, password, confirmPassword);
    }

    public void addCar(Car tempCar){
        if(cars == null) {
            cars = new ArrayList<>();
        }
        cars.add(tempCar);
        tempCar.setUser(this);
    }

    public void addExtraCost(ExtraCosts extraCosts){
        if(extraCostsList == null) {
            extraCostsList = new ArrayList<>();
        }
        extraCostsList.add(extraCosts);
        extraCosts.setTheUser(this);
    }

    public void addFuelCost(FuelCosts fuelCosts){
        if(fuelCostsList == null) {
            fuelCostsList = new ArrayList<>();
        }
        fuelCostsList.add(fuelCosts);
        fuelCosts.setTheUser(this);
    }
}

