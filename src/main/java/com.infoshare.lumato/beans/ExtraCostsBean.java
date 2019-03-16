package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.ExtraCostsService;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.utils.ExtraCostsComparatorByDate;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

@RequestScoped
@Named("extraCostBeans")
public class ExtraCostsBean {
    @Inject
    private ExtraCostsService extraCostsService;

    @Inject
    private CarsService carsService;

    @Inject
    private MessageService messageService;

    private String dateAsString;

    private ExtraCosts extraCosts = new ExtraCosts();

    private List<ExtraCosts> extraCostsList;

    private Car car = new Car();

    private List<Car> carList;

    public String getDateAsString() {
        return dateAsString;
    }

    public void setDateAsString(String dateAsString) {
        this.dateAsString = dateAsString;
    }

    public void setExtraCosts(ExtraCosts extraCosts) {
        this.extraCosts = extraCosts;
    }

    public FuelCosts getExtraCosts() {
        return extraCosts;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<ExtraCosts> getCompleteExtraCostsList() {
        extraCostsList.sort(new ExtraCostsComparatorByDate());
        return extraCostsList;
    }
    public List<Car> getCars() {
        return carList;
    }

    private void loadExtraCostsList() {
        extraCostsService = extraCostsService.getAllCarsByUser()
    }

    private void loadCars() {
        carList = extraCostsService.getAllCarsByUser();
    }

    @PostConstruct
    public void construct() {
        loadExtraCostsList();
        loadCars();
        Collections.sort(this.extraCostsList, new ExtraCostsComparatorByDate());
    }

    private void addExtraCosts(Car car) {
        extraCostsService.addExtraCosts(extraCosts, car);
        redirectToExtraCostsPage();
    }

    public void attemptToAddExtraCosts() {
        Calendar calendar = CalendarService.returnCalendarDateFromInputString(dateAsString);
        car = carsService.getCarById(car.getCarId());

        if (calendar != null && extraCostsService.isExtraCostsEmpty(extraCosts)) {
            this.extraCosts.setDate(calendar);
            addExtraCosts(car);
        } else {
            messageService.addMessageCookie("wrongCredentialsMessage", "Incorrect data input! Please try again!");
            extraCosts = null;
            redirectToExtraCostsPage();
        }
    }

    public void attemptToDeleteExtraCosts(ExtraCosts extraCosts) {
        setExtraCosts(extraCosts);
        deleteExtraCosts();
    }

    private void deleteExtraCosts() {
        extraCostsService.deleteExtraCost(extraCosts);
        redirectToExtraCostsPage();
    }

    private void redirectToExtraCostsPage() {
        HttpUtils.redirect("/app/extraCosts.xhtml");
    }
}
