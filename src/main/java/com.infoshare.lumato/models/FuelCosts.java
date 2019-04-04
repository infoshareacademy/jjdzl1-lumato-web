package com.infoshare.lumato.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;


//@Entity
//@Table(name = "fuelcosts")
public class FuelCosts {


//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "idfuelcost")
    private int id;

    private Calendar date;

   // @Column(name = "priceperliter")
    private double pricePerLiter;

    //@Column(name = "amountoffuel")
    private double amountOfFuel;

    //@Column(name = "currentmileage")
    private int currentMileage;

    //@Column(name = "typeoffuel")
    private String fuelType;

    //@Column(name = "idcar")
    private int idCar;


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

    public String getDateAsString() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(this.date.getTime());
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

    public int getIdCar() {
        return idCar;
    }

    public void setIdCar(int idCar) {
        this.idCar = idCar;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FuelCosts fuelCosts = (FuelCosts) o;
        return id == fuelCosts.id &&
                currentMileage == fuelCosts.currentMileage &&
                date.equals(fuelCosts.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, date, currentMileage);
    }
}
