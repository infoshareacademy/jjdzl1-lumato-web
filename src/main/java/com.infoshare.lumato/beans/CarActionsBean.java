package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.utils.FuelType;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named("carBean")
public class CarActionsBean implements Serializable {

    @Inject
    private CarsService carsService;

    @Inject
    private MessageService messageService;

    private Car car = new Car();

    private FuelType[] fuelTypes;

    private List<Car> carList;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @PostConstruct
    public void construct() {
        fuelTypes = FuelType.values();
        loadCars();
    }

    public FuelType[] getFuelTypes() {
        return fuelTypes;
    }

    public List<Car> getCars() {
        return carList;
    }

    private void loadCars() {
        try {
            carList = carsService.getAllCarsByUser();
        } catch (Exception e) {
            System.out.println("Cannot load users!");
            e.printStackTrace();
        }
    }

    private void addNewCar() {
        carsService.addCar(car);
        redirectToCarPage();
    }

    public void attemptToAddNewCar() {
        if (carsService.isFieldEmpty(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "All fields must be filled!");
            redirectToCarPage();
        }
        if (!carsService.isCarProductionYearValid(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "Invalid production year!");
            redirectToCarPage();
        }
        if (carsService.doesCarExist(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "Car with this registration number already exist!");
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

    private void redirectToCarPage() {
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    public String redirectToCarEdit(Car theCar) {
        setCar(theCar);
        return "/app/cars-edit.xhtml";
    }

    public void updateCar(Car car) {
        this.car = car;
        carsService.updateCar(car);
    }
}




























