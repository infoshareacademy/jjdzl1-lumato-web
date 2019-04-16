package com.infoshare.lumato.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Entity
@Table(name = "extracost")
public class ExtraCosts {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "cost")
    private double cost;

    @Column(name = "description")
    private String description;

    @Column(name = "car_id")
    private int carId;

    @Column(name = "cost_date")
    private Calendar date;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "car_id")
    private Car car;

    public ExtraCosts() {
    }

    public String getDateAsString() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(this.date.getTime());
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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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
