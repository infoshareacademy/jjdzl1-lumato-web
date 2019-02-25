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
        System.out.println(" Po zaincjalizowaniu carinDB " + carInDB);

        if (carInDB == null) {
            System.out.println("========== Car jest nullem CarService i zwraca false ==============!");
            return false;
        } else {
            System.out.println("========== Car nie jets nullem w CarService ==============!");
            System.out.println("=============== Rejestracja carInDB   " + carInDB.getRegPlate());
            if (carInDB.getRegPlate().equals(car.getRegPlate())) {
                System.out.println("================= Blachy sa rowne, nie mozna dodac!!!");
                return true;
            } else {
                System.out.println("B;achu sa rozne ");
                return false;
            }
        }
    }
}














































