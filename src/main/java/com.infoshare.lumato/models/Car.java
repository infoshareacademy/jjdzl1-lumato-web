package com.infoshare.lumato.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cars")
public class Car implements Serializable {

    private static final long serialVersionUID = 8242396670252535134L;

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    @Column(name = "idcars")
    private int carId;

    @Column(name = "model")
    private String model;

    @Column(name = "brand")
    private String brand;

    @Column(name = "year")
    private int productionYear;

    @Column(name = "iduser")
    private int idUserInCars;

    @Column(name = "fueltype")
    private String fuelType;

    @Column(name = "regplate")
    private String regPlate;


    public Car(){

    }

    public Car(int carId, int idUserInCars, String brand, String model, int productionYear, String fuelType, String regPlate) {
        this.carId = carId;
        this.idUserInCars = idUserInCars;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.regPlate = regPlate;
        this.fuelType = fuelType;
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
        this.regPlate = regPlate.toUpperCase().replace(" ","");
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public int getIdUserInCars() {
        return idUserInCars;
    }

    public void setIdUserInCars(int idUserInCars) {
        this.idUserInCars = idUserInCars;
    }
}

