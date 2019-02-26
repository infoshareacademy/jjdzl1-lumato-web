package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.models.Car;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import java.util.List;

@RequestScoped
public class CarsService {


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

    public boolean doesCarExist(Car car) {
        Car carInDB = carDAO.findCarByRegistrationPlate(car.getRegPlate());
        if (carInDB == null) return false;
        return carInDB.getRegPlate().equals(car.getRegPlate());
    }
}














































