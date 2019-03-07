package com.infoshare.lumato.models;

import java.util.Calendar;

class ExtraCosts {

    private int id;
    private double cost;
    private String description;
    private int carId;
    private Calendar date;

    public ExtraCosts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ExtraCosts{" +
                "id=" + id +
                ", cost=" + cost +
                ", description='" + description + '\'' +
                ", carId=" + carId +
                ", date=" + date +
                '}';
    }
}
