package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.dao.CarDAO;
import com.infoshare.lumato.logic.dao.FuelCostsDAO;
import com.infoshare.lumato.logic.dao.TokenDao;
import com.infoshare.lumato.logic.dao.UserDAO;
import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.User;

import com.infoshare.lumato.logic.utils.HttpUtils;
import org.apache.commons.lang3.math.NumberUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.math.RoundingMode;
import java.util.Locale;

@SessionScoped
@Named("globalAppBean")
public class StartViewBean implements Serializable {

    private static int amountOfUsers;
    private static int amountOfCars;
    private static String token;

    @Inject
    TokenDao tokenDao;

    @Inject
    UserDAO userDAO;

    @Inject
    CarDAO carDAO;

    @Inject
    FuelCostsDAO fuelCostsDAO;

    @PostConstruct
    public void construct() {
        amountOfCars = carDAO.countAllRecords(Car.class);
        amountOfUsers = userDAO.countAllRecords(User.class);
        token = tokenDao.getUserToken((Integer) HttpUtils.getCurrentUserFromSession().getUserId());
    }

    public String getCurrentUserToken() {
        return token;
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
