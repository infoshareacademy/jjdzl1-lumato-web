package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.ExtraCostService;
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
@Named("extraCostInputBean")
public class ExtraCostsInputBean implements Serializable {

    @Inject
    private ExtraCostService extraCostService;

    @Inject
    private CarsService carsService;

    @Inject
    private MessageService messageService;

    private int page;

    int itemsOnPage;

    int[] itemsShowOnPage = {4, 8, 12};

    List<Integer> pageList;

    private Car car = new Car();

    private List<Car> carList;

    private ExtraCosts extraCost = new ExtraCosts();

    private List<ExtraCosts> extraCosts;

    private String dateAsString;

    @PostConstruct
    public void construct() {
        loadCars();
    }

    public List getExtraCostList() {
        pageList = getListOfPages();
        getCurrentPage();
        return extraCosts = extraCostService.getCurrentItemsList();
    }

    private void loadCars() {
        carList = carsService.getAllCarsByUser();
    }

    public List<ExtraCosts> getCompleteExtraCostList() {
        return extraCosts;
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

    public void attemptToDeleteExtraCost(ExtraCosts theExtraCosts) {
        setExtraCost(theExtraCosts);
        deleteExtraCost();
    }

    private void deleteExtraCost() {
        extraCostService.deleteExtraCost(extraCost);
        redirectToExtraCostInputPage();
    }

    private void redirectToExtraCostInputPage() {
        HttpUtils.redirect("/app/cost-input.xhtml");
    }

    public void previousPage() {
        extraCostService.previousPage();
    }

    public void nextPage() {
        extraCostService.nextPage();
    }

    public void firstPage() {
        extraCostService.firstPage();
    }

    public void lastPage() {
        extraCostService.lastPage();
    }

    private void getCurrentPage() {
        page = extraCostService.getPage();
    }

    private List<Integer> getListOfPages() {
        pageList = new ArrayList<>();
        IntStream.rangeClosed(1, extraCostService.getNumberOfPages()).
                forEachOrdered(i -> pageList.add(i));
        return pageList;
    }

    public void goToSelectedPage() {
        extraCostService.setPage(page);
        extraCostService.getCurrentItemsList();
    }

    public void setNumberOfItemsOnPage() {
        extraCostService.setItemsOnPage(itemsOnPage);
        extraCostService.setPage(page = 1);
        extraCostService.getCurrentItemsList();
    }
}
