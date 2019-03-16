package com.infoshare.lumato.models;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class ExtraCosts {

    private int id;
    private double costs;
    private String description;
    private int carId;
    private int currentMileage;
    private Calendar date;

    public ExtraCosts() {
    }

    public ExtraCosts(int id, double costs, String description, int carId, int currentMileage, Calendar date) {
        this.id = id;
        this.costs = costs;
        this.description = description;
        this.carId = carId;
        this.currentMileage = currentMileage;
        this.date = date;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCosts() {
        return costs;
    }

    public void setCosts(double costs) {
        this.costs = costs;
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

    public int getCurrentMileage() {
        return currentMileage;
    }

    public void setCurrentMileage(int currentMileage) {
        this.currentMileage = currentMileage;
    }

    public String getDateAsString() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = format1.format(this.date.getTime());
        return dateAsString;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExtraCosts extraCosts = (ExtraCosts) o;
        return id == extraCosts.id &&
                currentMileage == extraCosts.currentMileage &&
                date.equals(extraCosts.date);
    }

    @Override
    public int hashCode() {return Objects.hash(id,date,currentMileage);}

}
