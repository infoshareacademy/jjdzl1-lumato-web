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
public class CarsService extends PaginationService implements Serializable, Service {

    @Inject
    CarDAO carDAO;

    public boolean isFieldEmpty(Car car) {
        return (car.getBrand().isEmpty() | car.getModel().isEmpty() | car.getRegPlate().isEmpty() | car.getFuelType().isEmpty());
    }

    public boolean doesObjectExist(Car car) {
        Car carInDB = carDAO.findCarByRegistrationPlate(car.getRegPlate());
        if (carInDB == null) {
            return false;
        }
        return carInDB.getRegPlate().equals(car.getRegPlate());
    }

    public boolean isCarProductionYearValid(Car car) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return (car.getProductionYear() >= 1908 && car.getProductionYear() <= currentYear);
    }

    public List<Car> getAllObjectsByUser() {
        return carDAO.getAllCarsByUser();
    }

    public Car getCarByRegPLate(String regPlate) {
        return carDAO.findCarByRegistrationPlate(regPlate);
    }

    @Override
    public void addObject(Object car) {
        carDAO.addOrUpdateCar((Car) car);
    }

    @Override
    public void deleteObject(Object car) {
        carDAO.deleteCar((Car) car);
    }

    @Override
    public void updateObject(Object car) {
        carDAO.addOrUpdateCar((Car) car);
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    @Override
    public Object getObjectById(int id) {
        return carDAO.findCarById(id);
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }

    @Override
    public int getNumberOfPages() {
        return carDAO.getNumberOfPages(itemsOnPage);
    }

    @Override
    public List<Car> getCurrentItemsList() {
        return carDAO.getCarsPerPage(page, itemsOnPage);
    }

    @Override
    public void setItemsOnPage(int itemsOnPage) {
        super.setItemsOnPage(itemsOnPage);
    }

    @Override
    public void firstPage() {
        super.firstPage();
    }

    @Override
    public void previousPage() {
        super.previousPage();
    }

    @Override
    public void nextPage() {
        super.nextPage();
    }

    @Override
    public void lastPage() {
        super.lastPage();
    }
}