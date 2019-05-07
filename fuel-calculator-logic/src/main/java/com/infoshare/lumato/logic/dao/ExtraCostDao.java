package com.infoshare.lumato.logic.dao;


import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;
import com.infoshare.lumato.logic.model.User;
import org.hibernate.Session;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
public class ExtraCostDao extends CommonDAO {

    public void addExtraCostByCarId(ExtraCosts extraCosts, Car car) {
        Session currentSession = getSession();
        User tempUser = currentSession.get(User.class, currentUser.getUserId());
        Car tempCar = currentSession.get(Car.class, car.getCarId());
        tempUser.addExtraCost(extraCosts);
        tempCar.addExtraCost(extraCosts);
        executeAndCloseTransaction(currentSession);
    }
}