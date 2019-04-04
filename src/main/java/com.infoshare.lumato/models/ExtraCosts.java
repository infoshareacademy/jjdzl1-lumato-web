package com.infoshare.lumato.models;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Entity
@Table(name = "extracost")
public class ExtraCosts {


    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idextracosts")
    private int id;

    @Column(name = "cost")
    private double cost;

    @Column(name = "description")
    private String description;

    @Column(name = "idcars")
    private int carId;

    @Column(name = "costdate")
    private Calendar date;

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
