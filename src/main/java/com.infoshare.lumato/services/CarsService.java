package com.infoshare.lumato.services;

import com.infoshare.lumato.logic.dao.CarDAO;
import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.utils.HttpUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@ViewScoped
public class CarsService implements Serializable {

    private int page = 1;

    private final int carsOnPage = 4;

    @Inject
    CarDAO carDAO;

    public void addCar(Car car) {
        carDAO.addOrUpdateCar(car);
    }

    public void deleteCar(Car car) {
        carDAO.deleteCar(car);
    }

    public void updateCar(Car car) {
        carDAO.addOrUpdateCar(car);
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    public List getCurrentItemsList() {
        return carDAO.getCarsPerPage(page, carsOnPage);
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

    public void firstPage() {
        page = 1;
    }

    public void lastPage() {
        page = carDAO.getNumberOfPages(carsOnPage);
    }

    public void previousPage() {
        if (page <= 1) {
            page = 1;
        } else page--;
    }

    public void nextPage() {
        int lastPage = carDAO.getNumberOfPages(carsOnPage);
        if (page == lastPage) page = lastPage - 1;
        page++;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurrentPage() {
        return page;
    }
}