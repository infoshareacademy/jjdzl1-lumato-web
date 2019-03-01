package com.infoshare.lumato.beans;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named("fuelInputBean")
public class FuelInputBean implements Serializable {

    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    private CarsService carsService;

    private FuelCosts fuelCost = new FuelCosts();

    private Car car = new Car();

    private List<Car> carList;

    public void setFuelCost(FuelCosts fuelCost) {
        this.fuelCost = fuelCost;
    }

    public FuelCosts getFuelCost() {
        return fuelCost;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return car;
    }

    public List<Car> getCars() {
        return carList;
    }

    @PostConstruct
    public void construct() {
        loadCars();
    }

    private void loadCars() {
        carList = fuelsCostsService.getAllCarsByUser();
    }

    public void attemptToAddFuelCost() {
        addFuelCost();
    }

    private void addFuelCost() {
        car = carsService.getCarByRegPLate(car.getRegPlate());
        fuelsCostsService.addFuelCost(fuelCost, car);
        HttpUtils.redirect("/app/cars-input.xhtml");

    }


}
