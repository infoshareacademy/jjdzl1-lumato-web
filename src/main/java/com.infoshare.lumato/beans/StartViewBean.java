package com.infoshare.lumato.beans;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.dao.FuelCostsDAO;
import com.infoshare.lumato.dao.UserDAO;
import com.infoshare.lumato.models.User;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Locale;

@SessionScoped
@Named("globalAppBean")
public class StartViewBean implements Serializable {

    private int amountOfUsers;
    private int amountOfCars;

    @Inject
    UserDAO userDAO;

    @Inject
    CarDAO carDAO;

    @Inject
    FuelCostsDAO fuelCostsDAO;

    @PostConstruct
    public void construct() {
        amountOfCars = carDAO.countAllRecords("cars");
        amountOfUsers = userDAO.countAllRecords("users");
    }

    public int countAllUsers(){
        return amountOfUsers;
    }

    public int countAllCars(){
        return amountOfCars;
    }

    public String calculateAverageFuelCost(String fuelType){
        double averageFuelCost = fuelCostsDAO.calculateAverageFuelCost(fuelType);
        double av =  NumberUtils.toScaledBigDecimal(averageFuelCost, 2, RoundingMode.HALF_UP).doubleValue();
        return String.format(Locale.CANADA,"%.2f", av);
    }

}
