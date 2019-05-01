package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.utils.FuelType;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Setter
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

    private int page;

    List<Integer> pageList;

    @PostConstruct
    public void construct() {
        fuelTypes = FuelType.values();
        pageList = getListOfPages();
    }

    public List getCars() {
        getCurrentPage();
        return carList = carsService.getCurrentItemsList();
    }

    private void addNewCar() {
        carsService.addCar(car);
        redirectToCarPage();
    }

    private void deleteCar() {
        carsService.deleteCar(car);
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
        if (carsService.doesCarExist(car)) {
            messageService.addMessageCookie("wrongCredentialsMessage", "Car with this registration number already exists!");
            car = null;
            redirectToCarPage();
        } else addNewCar();
    }

    public void attemptToDeleteCar(Car theCar) {
        setCar(theCar);
        deleteCar();
    }

    public void updateCar(Car car) {
        this.car = car;
        carsService.updateCar(car);
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

    public void previousPage() {
        carsService.previousPage();
    }

    public void nextPage() {
        carsService.nextPage();
    }

    public void firstPage() {
        carsService.firstPage();
    }

    public void lastPage() {
        carsService.lastPage();
    }

    private void getCurrentPage() {
        page = carsService.getPage();
    }

    private List<Integer> getListOfPages() {
        pageList = new ArrayList<>();
        IntStream.rangeClosed(1, carsService.getNumberOfPages()).
                forEachOrdered(i -> pageList.add(i));
        return pageList;
    }

    public void goToSelectedPage() {
        carsService.setPage(page);
        carsService.getCurrentItemsList();
    }

}