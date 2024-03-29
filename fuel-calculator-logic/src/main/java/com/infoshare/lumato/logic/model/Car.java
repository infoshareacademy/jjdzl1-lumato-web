package com.infoshare.lumato.logic.model;


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


    @OneToMany(mappedBy = "car",
            cascade = CascadeType.ALL)
    private List<FuelCosts> fuelCostsList;

    public void setModel(String model) {
        this.model = model.toUpperCase();
    }

    public void setBrand(String brand) {
        this.brand = brand.toUpperCase();
    }

    public void setRegPlate(String regPlate) {
        this.regPlate = regPlate.toUpperCase();
    }

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

    public void addFuelCost(FuelCosts fuelCosts){
        if(fuelCostsList == null) {
            fuelCostsList = new ArrayList<>();
        }
        fuelCostsList.add(fuelCosts);
        fuelCosts.setCar(this);
    }
}