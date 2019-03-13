package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RequestScoped
@Named("userDataBean")
public class UserDataBean implements Serializable {

    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    private CarsService carsService;

    private List<FuelCosts> fuelCostsList;

    private List<Car> carList;

    public void setFuelCostsList(List<FuelCosts> fuelCostsList) {
        this.fuelCostsList = fuelCostsList;
    }

    public List<FuelCosts> getFuelCostsList() {
        return fuelCostsList;
    }

    public List<Car> getCars() {
        return carList;
    }

    private void loadFuelCostList() {
        fuelCostsList = fuelsCostsService.getAllFuelCostsByUser();
    }

    private void loadCars() {
        carList = fuelsCostsService.getAllCarsByUser();
    }

    @PostConstruct
    public void construct() {
        loadFuelCostList();
        loadCars();
        sortByDate();
    }

    public void sortByDate(){
        Collections.sort(this.fuelCostsList, Comparator.comparing(FuelCosts::getDate));
    }

    public void sortByDateReversed(){
        Collections.sort(this.fuelCostsList, Comparator.comparing(FuelCosts::getDate).reversed());
    }

    public String getGetCarAsString(Integer idOfCar) {
        Car car = carList.stream()
                        .filter(c -> c.getCarId() == idOfCar)
                        .findFirst()
                        .get();
        return car.getBrand() + " " + car.getModel() + " [" + car.getRegPlate() + "]";
    }
}