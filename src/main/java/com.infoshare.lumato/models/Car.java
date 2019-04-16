package com.infoshare.lumato.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand.toUpperCase();
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model.toUpperCase();
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getRegPlate() {
        return regPlate;
    }

    public void setRegPlate(String regPlate) {
        this.regPlate = regPlate.toUpperCase().replace(" ", "");
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public User getUser() {
        return theUser;
    }

    public void setUser(User user) {
        this.theUser = user;
    }

    public User getTheUser() {
        return theUser;
    }

    public void setTheUser(User theUser) {
        this.theUser = theUser;
    }

    public void addExtraCost(ExtraCosts extraCost){
        if(extraCostsList == null) {
            extraCostsList = new ArrayList<>();
        }
        extraCostsList.add(extraCost);
        extraCost.setCar(this);
    }

}