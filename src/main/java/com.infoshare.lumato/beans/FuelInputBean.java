package com.infoshare.lumato.beans;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named("fuelInputBean")
public class FuelInputBean {

    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    CarDAO carDAO;

    private FuelCosts fuelCost;

    private Car car = new Car();

    private List<Car> carList;
    private CarsService carsService;

    public void setFuelCost(FuelCosts fuelCost){
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

    public void addFuelCost(FuelCosts fuelCosts) {
        setCar(car);
        fuelsCostsService.addFuelCost(fuelCosts, car);
    }


}
