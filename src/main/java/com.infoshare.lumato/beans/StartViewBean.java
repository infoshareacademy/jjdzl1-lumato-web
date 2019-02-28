package com.infoshare.lumato.beans;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.dao.FuelCostsDAO;
import com.infoshare.lumato.dao.UserDAO;
import org.apache.commons.lang3.math.NumberUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.math.RoundingMode;

@RequestScoped
@Named("startBean")
public class StartViewBean {

    @Inject
    UserDAO userDAO;

    @Inject
    CarDAO carDAO;

    @Inject
    FuelCostsDAO fuelCostsDAO;

    public int countAllUsers(){
        return userDAO.countAllRecords("users");
    }

    public int countAllCars(){
        return carDAO.countAllRecords("cars");
    }

    public double calculateAverageFuelCost(String fuelType){
        double averageFuelCost = fuelCostsDAO.calculateAverageFuelCost(fuelType);
        return NumberUtils.toScaledBigDecimal(averageFuelCost, 2, RoundingMode.HALF_UP).doubleValue();
    }

}
