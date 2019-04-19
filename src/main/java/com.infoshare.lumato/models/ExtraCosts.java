package com.infoshare.lumato.models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@Setter
@Getter
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

    @Column(name = "car_id", insertable = false, updatable = false)
    private int carId;

    @Column(name = "cost_date")
    private Calendar date;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "car_id")
    private Car car;


    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "user_id")
    private User theUser;

    public ExtraCosts() {
    }

    public String getDateAsString() {
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        return format1.format(this.date.getTime());
    }
}
