package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.FuelCosts;
import com.infoshare.lumato.logic.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class FuelCostsDAO extends CommonDAO {

    private final int userId = currentUser.getUserId();

    public void addFuelCostByCarId(FuelCosts fuelCosts, Car tempCar) {
        fuelCosts.setFuelType(tempCar.getFuelType());
        Session currentSession = getSession();
        User tempUser = currentSession.get(User.class, userId);
        Car car = currentSession.get(Car.class, tempCar.getCarId());
        tempUser.addFuelCost(fuelCosts);
        car.addFuelCost(fuelCosts);
        executeAndCloseTransaction(currentSession);
    }

    public Double calculateAverageFuelCost(String fuelType) {
        double singleResult = 0;

        Session currentSession = getSession();
        String hQuery =
                "SELECT AVG(F.pricePerLiter) FROM FuelCosts F WHERE F.fuelType=:theFuelType";
        Query query =
                currentSession.createQuery(hQuery, Double.class).setParameter("theFuelType", fuelType);
        List list = query.getResultList();
        executeAndCloseTransaction(currentSession);

        if (list.get(0) == null) {
            return singleResult;
        } else {
            return (Double) list.get(0);
        }
    }
}