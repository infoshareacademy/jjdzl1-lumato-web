package com.infoshare.lumato.models;

import java.util.Calendar;

public class FuelCosts {

    private int id;
    private Calendar date;
    private double pricePerLiter;
    private double amountOfFuel;
    private int currentMileage;
    private String fuelType;
    private int carId;

    public FuelCosts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public double getPricePerLiter() {
        return pricePerLiter;
    }

    public void setPricePerLiter(double pricePerLiter) {
        this.pricePerLiter = pricePerLiter;
    }

    public double getAmountOfFuel() {
        return amountOfFuel;
    }

    public void setAmountOfFuel(double amountOfFuel) {
        this.amountOfFuel = amountOfFuel;
    }

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    @Override
    public String toString() {
        return "FuelCosts{" +
                "id=" + id +
                ", date=" + date +
                ", pricePerLiter=" + pricePerLiter +
                ", amountOfFuel=" + amountOfFuel +
                ", currentMileage=" + currentMileage +
                ", fuelType='" + fuelType + '\'' +
                ", carId=" + carId +
                '}';
    }
}