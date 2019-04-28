package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.ExtraCostService;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.logic.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@RequestScoped
@Named("extraCostInputBean")
public class ExtraCostsInputBean implements Serializable {

    @Inject
    private ExtraCostService extraCostService;

    @Inject
    private CarsService carsService;

    @Inject
    private MessageService messageService;

    private ExtraCosts extraCost = new ExtraCosts();

    private List<ExtraCosts> extraCostsList;

    private Car car = new Car();

    private List<Car> carList;

    private String dateAsString;

    public ExtraCosts getExtraCost() {
        return extraCost;
    }

    public void setExtraCost(ExtraCosts extraCost) {
        this.extraCost = extraCost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<ExtraCosts> getCompleteExtraCostList() {
        return extraCostsList;
    }

    @PostConstruct
    public void construct() {
        loadExtraCostsList();
        loadCars();
    }

    private void loadExtraCostsList() {
        extraCostsList = extraCostService.getAllExtraCostsByUser();
    }

    private void loadCars() {
        carList = carsService.getAllCarsByUser();
    }

    public void attemptToAddExtraCost() {
        Calendar calendar = CalendarService.returnCalendarDateFromInputString(dateAsString);
        car = carsService.getCarByRegPLate(car.getRegPlate());

        if (calendar != null ) {
            this.extraCost.setDate(calendar);
            addExtraCost(car);
        } else {
            messageService.addMessageCookie("wrongCredentialsMessage", "Date field cannot be empty! Please try again!");
            extraCost = null;
            redirectToExtraCostInputPage();
        }
    }

    private void addExtraCost(Car car) {
        extraCostService.addExtraCost(extraCost, car);
        redirectToExtraCostInputPage();
    }

    private void redirectToExtraCostInputPage() {
        HttpUtils.redirect("/app/cost-input.xhtml");
    }

    public void attemptToDeleteExtraCost(ExtraCosts theExtraCosts) {
        setExtraCost(theExtraCosts);
        deleteExtraCost();
    }

    private void deleteExtraCost() {
        extraCostService.deleteExtraCost(extraCost);
        redirectToExtraCostInputPage();
    }

    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }

    public String getDateAsString() {
        return dateAsString;
    }
}
