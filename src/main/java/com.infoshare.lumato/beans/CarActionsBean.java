package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.utils.FuelType;
import com.infoshare.lumato.utils.HttpUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Setter(AccessLevel.PRIVATE)
@Getter
@RequestScoped
@Named("carBean")
public class CarActionsBean implements Serializable {

    @Inject
    private CarsService carsService;

    @Inject
    private MessageService messageService;

    private Car car = new Car();

    private FuelType[] fuelTypes;

    private List carList;


    @PostConstruct
    public void construct() {
        fuelTypes = FuelType.values();
    }


    public List getCars() {
        return carList = carsService.getCurrentPage();
    }

    private void addNewCar() {
        carsService.addCar(car);
        redirectToCarPage();
    }

    // TODO: 03.03.2019 put call==null in methods
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
        if (carsService.doesCarExist(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "Car with this registration number already exists!");
            car = null;
            redirectToCarPage();
        } else addNewCar();
    }

    private void deleteCar() {
        carsService.deleteCar(car);
        redirectToCarPage();
    }

    public void attemptToDeleteCar(Car theCar) {
        setCar(theCar);
        deleteCar();
    }

    public void updateCar(Car car) {
        this.car = car;
        carsService.updateCar(car);
    }

    public void nextPage() {
        carsService.nextPage();
    }

    public void previousPage() {
        carsService.previousPage();
    }

    public void redirectToCarPage() {
        this.car = null;
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    public String redirectToCarEdit(Car theCar) {
        setCar(theCar);
        return "/app/cars-edit.xhtml";
    }

    public Car getCarById(int id) {
        return carsService.getCarById(id);
    }
}