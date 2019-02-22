package com.infoshare.lumato.models;

import java.io.Serializable;


public class Car implements Serializable {
    private int idUserInCars;
    private String brand;
    private String model;
    private int productionYear;
    private String fuelType;
    private String comment;

    public Car(){

    }

    public Car(int idUserInCars, String brand, String model, int productionYear, String fuelType, String additionalComment) {
        this.idUserInCars = idUserInCars;
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.comment = additionalComment;
        this.fuelType = fuelType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getIdUserInCars() {
        return idUserInCars;
    }

    public void getIdUserInCars(int iduser) {
        this.idUserInCars = iduser;
    }
}
