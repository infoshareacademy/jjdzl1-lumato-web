package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;
import com.infoshare.lumato.logic.utils.HttpUtils;
import com.infoshare.lumato.services.CalendarService;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.ExtraCostService;
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
@Named("extraCostInputBean")
public class ExtraCostsInputBean extends Bean implements Serializable {

    @Inject
    ExtraCostService extraCostService = new ExtraCostService();

    @Inject
    private CarsService carsService;

    private ExtraCosts extraCost = new ExtraCosts();

    private List<ExtraCosts> extraCosts;

    @PostConstruct
    public void construct() {
        loadCars();
    }

    public List getExtraCostList() {
        super.setService(extraCostService);
        pageList = getListOfPages();
        getCurrentPage();
        return extraCosts = extraCostService.getCurrentItemsList();
    }

    private void loadCars() {
        carList = carsService.getAllObjectsByUser();
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

    public void deleteExtraCost(ExtraCosts theExtraCosts) {
        setExtraCost(theExtraCosts);
        extraCostService.deleteObject(extraCost);
        redirectToExtraCostInputPage();
    }

    private void redirectToExtraCostInputPage() {
        HttpUtils.redirect("/app/cost-input.xhtml");
    }
}
