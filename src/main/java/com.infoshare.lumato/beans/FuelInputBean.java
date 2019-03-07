package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@RequestScoped
@Named("fuelInputBean")
public class FuelInputBean implements Serializable {

    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    private CarsService carsService;

    public String getDateAsString() {
        return dateAsString;
    }

    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }

    private String dateAsString;

    private FuelCosts fuelCost = new FuelCosts();

    private List<FuelCosts> fuelCostsList;

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

    public void setFuelCostsList(List<FuelCosts> fuelCostsList) {
        this.fuelCostsList = fuelCostsList;
    }

    public List<FuelCosts> getFuelCostsList() {
        return fuelCostsList;
    }

    public List<Car> getCars() {
        return carList;
    }

    @PostConstruct
    public void construct() {
        loadFuelCostList();
        loadCars();
    }

    private void loadFuelCostList() {
        fuelCostsList = fuelsCostsService.getAllFuelCostsByUser();
    }

    private void loadCars() {
        carList = fuelsCostsService.getAllCarsByUser();
    }

    public void attemptToAddFuelCost() {
        Calendar calendar = new GregorianCalendar();
        calendar = CalendarService.returnCalendarDate(dateAsString);
        if (calendar != null) {
            this.fuelCost.setDate(calendar);
            addFuelCost();
        } else {
            redirectToFuelInputPage();
        }
    }

    private void addFuelCost() {
        car = carsService.getCarByRegPLate(car.getRegPlate());
        fuelsCostsService.addFuelCost(fuelCost, car);
        redirectToFuelInputPage();
    }

    private void redirectToFuelInputPage() {
        HttpUtils.redirect("/app/fuel-input.xhtml");

    }
}
