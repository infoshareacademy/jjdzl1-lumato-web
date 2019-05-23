package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.utils.HttpUtils;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.utils.FuelType;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Setter
@Getter
@RequestScoped
@Named("carBean")
public class CarActionsBean extends Bean implements Serializable {

    @Inject
    CarsService carsService = new CarsService();

    private FuelType[] fuelTypes;

    @PostConstruct
    public void construct() {
        fuelTypes = FuelType.values();
        super.setService(carsService);
    }

    public List getCars() {
        pageList = getListOfPages();
        getCurrentPage();
        return carList = carsService.getCurrentItemsList();
    }

    private void addNewObject() {
        carsService.addObject(car);
        redirectToCarPage();
    }

    public void deleteCar(Car theCar) {
        setCar(theCar);
        carsService.deleteObject(car);
        redirectToCarPage();
    }

    public void attemptToAddNewCar() {
        if (carsService.isFieldEmpty(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "All fields must be filled!");
            car = null;
            redirectToCarPage();
        }
        if (!carsService.isCarProductionYearValid(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "Invalid production year!");
            car = null;
            redirectToCarPage();
        }
        if (carsService.doesObjectExist(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "Car with this registration number already exists!");
            car = null;
            redirectToCarPage();
        } else addNewObject();
    }

    public void updateCar(Car car) {
        this.car = car;
        carsService.updateObject(car);
    }

    public void redirectToCarPage() {
        this.car = null;
        HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "/app/cars-input.xhtml");
    }

    public String redirectToCarEdit(Car theCar) {
        setCar(theCar);
        return "/app/cars-edit.xhtml";
    }

    public Car getObjectById(int id) {
        return (Car) carsService.getObjectById(id);
    }
}