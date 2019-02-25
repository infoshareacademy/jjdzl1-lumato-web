package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
public class CarsService {


    @Inject
    DBConnection myConn;

    @Inject
    CarDAO carDAO;

    public void addCar(Car car) {
        carDAO.addCar(car);
    }


    public void deleteCar(Car car) {
        carDAO.deleteCar(car);
    }

    public List<Car> getAllCarsByUser() {
        return carDAO.getAllCarsByUser();
    }






}









































