package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@ViewScoped
public class CarsService implements Serializable {

    private int page = 1;

    private int carsOnPage = 4;

    @Inject
    CarDAO carDAO;

    public void addCar(Car car) {
        carDAO.addOrUpdateCar(car);
    }

    public void deleteCar(Car car) {
        carDAO.deleteCar(car);
    }

    public List<Car> getAllCarsByUser() {
        return carDAO.getAllCarsByUser();
    }

    public List<Car> getCurrentPage() {
        System.out.println("\nCurrent page is: " + page);
        return carDAO.getCarsPerPage(page, carsOnPage);
    }

    public boolean doesCarExist(Car car) {
        Car carInDB = carDAO.findCarByRegistrationPlate(car.getRegPlate());
        if (carInDB == null) {
            return false;
        }
        return carInDB.getRegPlate().equals(car.getRegPlate());
    }

    public void updateCar(Car car) {
        carDAO.addOrUpdateCar(car);
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    public boolean isFieldEmpty(Car car) {
        return (car.getBrand().isEmpty() | car.getModel().isEmpty() | car.getRegPlate().isEmpty() | car.getFuelType().isEmpty());
    }

    public boolean isCarProductionYearValid(Car car) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return (car.getProductionYear() >= 1908 && car.getProductionYear() <= currentYear);
    }

    public Car getCarByRegPLate(String regPlate) {
        return carDAO.findCarByRegistrationPlate(regPlate);
    }

    public Car getCarById(int id) {
        return carDAO.findCarById(id);
    }

    public void nextPage() {
        int lastPage = carDAO.getNumberOfPages(carsOnPage);
//        if (page < 1) page = 1;
        if (page == lastPage) page = lastPage - 1;
        page++;
//        return carDAO.getCarsPerPage(++page, carsOnPage);
    }

    public void previousPage() {
        if (page <= 1) {
            page = 1;
//            return carDAO.getCarsPerPage(page, carsOnPage);
        }
        page--;
//        return carDAO.getCarsPerPage(--page, carsOnPage);
    }
}