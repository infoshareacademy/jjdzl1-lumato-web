package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.FuelCosts;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Setter
@Getter
@RequestScoped
@Named("fuelInputBean")
public class FuelInputBean extends Bean implements Serializable {


    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    private CarsService carsService;

    private FuelCosts fuelCost = new FuelCosts();

    private List<FuelCosts> fuelCostsList;

    public List<Car> getCars() {
        return carList;
    }

    @PostConstruct
    public void construct() {
        loadCars();
        super.setService(fuelsCostsService);
    }

    public List getFuelCostList() {
        pageList = getListOfPages();
        getCurrentPage();
        return fuelCostsList = fuelsCostsService.getCurrentItemsList();
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

    public void deleteFuelCost(FuelCosts theFuelCost) {
        setFuelCost(theFuelCost);
        fuelsCostsService.deleteObject(fuelCost);
        redirectToFuelInputPage();
    }

    private void redirectToFuelInputPage() {
        HttpUtils.redirect("/app/fuel-input.xhtml");
    }

    public List<FuelCosts> getCompleteFuelCostsList() {
        return fuelCostsList;
    }

    private void loadCars() {
        carList = carsService.getAllObjectsByUser();
    }
}
