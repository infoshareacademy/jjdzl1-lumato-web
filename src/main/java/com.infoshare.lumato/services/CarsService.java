package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import java.util.List;
import java.util.Map;

@RequestScoped
public class CarsService {


    @Inject
    CarDAO carDAO;

    private Car car = new Car();

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

    public void editCar(Car car) {
        carDAO.editCar(car);
    }

    public void loadCarById(int carId) {
        car = carDAO.loadCarById(carId);
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> requestMap = externalContext.getRequestMap();
        requestMap.put("theCar", car);

    }
}














































