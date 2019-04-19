package com.infoshare.lumato.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

@Setter
@Getter
@Entity
@Table(name = "fuelcost")
public class FuelCosts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    private Calendar date;

    @Column(name = "price_per_liter")
    private double pricePerLiter;

    @Column(name = "amount_of_fuel")
    private double amountOfFuel;

    @Column(name = "current_mileage")
    private int currentMileage;

    @Column(name = "type_of_fuel")
    private String fuelType;

    @Column(name = "car_id", insertable = false, updatable = false)
    private int idCar;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User theUser;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "car_id")
    private Car car;


    public FuelCosts() {
    }

    public String getDateAsString() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(this.date.getTime());
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
