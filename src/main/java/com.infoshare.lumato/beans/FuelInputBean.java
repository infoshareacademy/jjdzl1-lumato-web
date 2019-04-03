package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;

@RequestScoped
@Named("fuelInputBean")
public class FuelInputBean implements Serializable {

    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    private CarsService carsService;

    @Inject
    private MessageService messageService;

    private FuelCosts fuelCost = new FuelCosts();

    private List<FuelCosts> fuelCostsList;

    private Car car = new Car();

    private List<Car> carList;

    private String dateAsString;

    public FuelCosts getFuelCost() {
        return fuelCost;
    }

    public void setFuelCost(FuelCosts fuelCost) {
        this.fuelCost = fuelCost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getCars() {
        return carList;
    }

    public List<FuelCosts> getCompleteFuelCostsList() {
        fuelCostsList.sort(new FuelCostComparatorByDate());
        return fuelCostsList;
    }

    private void loadFuelCostList() {
        fuelCostsList = fuelsCostsService.getAllFuelCostsByUser();
    }

    private void loadCars() {
        carList = carsService.getAllCarsByUser();
    }

    @PostConstruct
    public void construct() {
        loadFuelCostList();
        loadCars();
        Collections.sort(this.fuelCostsList, new FuelCostComparatorByDate());
    }

    private void addFuelCost(Car car) {
        fuelsCostsService.addFuelCost(fuelCost, car);
        redirectToFuelInputPage();
    }

    public void attemptToAddFuelCost() {
        Calendar calendar = CalendarService.returnCalendarDateFromInputString(dateAsString);
        car = carsService.getCarByRegPLate(car.getRegPlate());

        if (calendar != null && fuelsCostsService.isFuelAmountAndPriceNotEmpty(fuelCost) & fuelsCostsService.isMileageCorrect(fuelCost, calendar)) {
            this.fuelCost.setDate(calendar);
            addFuelCost(car);
        } else {
            messageService.addMessageCookie("wrongCredentialsMessage", "Incorrect data input! Please try again!");
            fuelCost = null;
            redirectToFuelInputPage();
        }
    }

    public void attemptToDeleteFuelCost(FuelCosts theFuelCost) {
        setFuelCost(theFuelCost);
        deleteFuelCost();
    }

    private void deleteFuelCost() {
        fuelsCostsService.deleteFuelCost(fuelCost);
        redirectToFuelInputPage();
    }

    private void redirectToFuelInputPage() {
        HttpUtils.redirect("/app/fuel-input.xhtml");
    }

    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }

    public String getDateAsString() {
        return dateAsString;
    }
}