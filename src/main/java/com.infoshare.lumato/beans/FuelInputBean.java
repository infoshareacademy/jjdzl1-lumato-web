package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.FuelCosts;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;
import com.infoshare.lumato.services.MessageService;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.IntStream;

@Setter
@Getter
@RequestScoped
@Named("fuelInputBean")
public class FuelInputBean implements Serializable {


    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    private CarsService carsService;

    @Inject
    private MessageService messageService;

    int page;

    int itemsOnPage;

    int[] itemsShowOnPage = {4, 8, 12};

    List<Integer> pageList;

    private Car car = new Car();

    private List<Car> carList;

    private FuelCosts fuelCost = new FuelCosts();

    private List<FuelCosts> fuelCostsList;

    public List<Car> getCars() {
        return carList;
    }

    private String dateAsString;

    @PostConstruct
    public void construct() {
        loadCars();
    }

    public List getFuelCosts() {
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

    public void attemptToDeleteFuelCost(FuelCosts theFuelCost) {
        setFuelCost(theFuelCost);
        deleteFuelCost();
    }

    private void deleteFuelCost() {
        fuelsCostsService.deleteFuelCost(fuelCost);
        redirectToFuelInputPage();
    }

    private void redirectToFuelInputPage() {
        HttpUtils.redirect(HttpUtils.getRequest().getContextPath() + "app/fuel-input.xhtml");
    }

    public List<FuelCosts> getCompleteFuelCostsList() {
        return fuelCostsList;
    }

    private void loadCars() {
        carList = carsService.getAllCarsByUser();
    }

    public void previousPage() {
        fuelsCostsService.previousPage();
    }

    public void nextPage() {
        fuelsCostsService.nextPage();
    }

    public void firstPage() {
        fuelsCostsService.firstPage();
    }

    public void lastPage() {
        fuelsCostsService.lastPage();
    }

    private void getCurrentPage() {
        page = fuelsCostsService.getPage();
    }

    private List<Integer> getListOfPages() {
        pageList = new ArrayList<>();
        IntStream.rangeClosed(1, fuelsCostsService.getNumberOfPages()).
                forEachOrdered(i -> pageList.add(i));
        return pageList;
    }

    public void goToSelectedPage() {
        fuelsCostsService.setPage(page);
        fuelsCostsService.getCurrentItemsList();
    }

    public void setNumberOfItemsOnPage() {
        fuelsCostsService.setItemsOnPage(itemsOnPage);
        fuelsCostsService.setPage(page = 1);
        fuelsCostsService.getCurrentItemsList();
    }
}