package com.infoshare.lumato.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "car")
public class Car implements Serializable {

    private static final long serialVersionUID = 8242396670252535134L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int carId;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "production_year")
    private int productionYear;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "reg_plate")
    private String regPlate;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User theUser;

    @OneToMany(mappedBy = "car",
            cascade = CascadeType.ALL)
    private List<ExtraCosts> extraCostsList;

    public Car() {
    }

    public User getUser() {
        return theUser;
    }

    public void setUser(User user) {
        this.theUser = user;
    }

    public void addExtraCost(ExtraCosts extraCost){
        if(extraCostsList == null) {
            extraCostsList = new ArrayList<>();
        }
        extraCostsList.add(extraCost);
        extraCost.setCar(this);
    }

}