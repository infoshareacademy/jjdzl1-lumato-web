package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named("carBean")
public class CarActionsBean {

    @Inject
    private CarsService carsService;

    private Car car;

    private List<Car> carList;

    public Car getCar(){
        return car;
    }

    public void setCar(Car car){
        this.car = car;
    }

    @PostConstruct
    public void construct() {
        car = new Car();
    }

    public List<Car> getCars(){
        loadCars();
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
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    private void deleteCar(){
        carsService.deleteCar(car);
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

    public void attemptToAddCar(){
        addNewCar();
    }

    public void attemptToDeleteCar(Car theCar){
        deleteCar();
    }









}




























