package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.utils.HttpUtils;

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
        if (carInDB == null) {
            return false;
        }
        return carInDB.getRegPlate().equals(car.getRegPlate());
    }

    public void updateCar(Car car) {
        carDAO.updateCar(car);
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    public boolean isFieldEmpty(Car car) {
        return (car.getBrand().isEmpty() | car.getModel().isEmpty() | car.getRegPlate().isEmpty() | car.getFuelType().isEmpty());
    }

    public boolean isCarProductionYearValid(Car car) {
        return (car.getProductionYear() >= 1908 && car.getProductionYear() <= 2019);
    }

    public Car getCarByRegPLate (String regPlate) {
        return carDAO.findCarByRegistrationPlate(regPlate);
    }

    public Car getCarById (int id) {
        return carDAO.findCarById(id);
    }
}














































