package com.infoshare.lumato.models;

import java.io.Serializable;


public class Car implements Serializable {

    private static final long serialVersionUID = 8242396670252535134L;
    private int carId;
    private int idUserInCars;
    private String brand;
    private String model;
    private int productionYear;
    private String fuelType;
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

    public String getRegPlate() {
        return regPlate;
    }

    public void setRegPlate(String regPlate) {
        this.regPlate = regPlate;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }
}
