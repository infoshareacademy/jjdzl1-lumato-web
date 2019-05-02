package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.FuelCosts;
import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class FuelCostsDAO extends CommonDAO {

    private final User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

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

    public List<FuelCosts> getAllFuelCostByUser() {
        Session currentSession = getSession();
        String hQuery = "FROM FuelCosts F WHERE F.theUser.id=:userId";
        Query<FuelCosts> query =
                currentSession.createQuery(hQuery, FuelCosts.class).setParameter("userId", userId);
        List<FuelCosts> fuelCostsList = query.getResultList();
        executeAndCloseTransaction(currentSession);
        return fuelCostsList;
    }

    public void deleteFuelCost(FuelCosts fuelCosts) {
        Session currentSession = getSession();
        currentSession.delete(fuelCosts);
        executeAndCloseTransaction(currentSession);
    }

    public int getNumberOfPages(int itemsOnPage) {
        double numberOfPages = Math.ceil(countFuelingByUser() / itemsOnPage);
        return countFuelingByUser() % itemsOnPage != 0 ? (int) numberOfPages + 1 : (int) numberOfPages;
    }

    private Long countFuelingByUser() {
        Session currentSession = getSession();
        String countQ =
                "select count (F.id) from FuelCosts F where F.theUser.id=:userId";
        Query countQuery =
                currentSession.createQuery(countQ).setParameter("userId", userId);
        Long numberOfCars = (Long) countQuery.uniqueResult();
        executeAndCloseTransaction(currentSession);
        return numberOfCars;
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

    public List getItemsPerPage(int pageNumber, int pageSize) {
        Session currentSession = getSession();
        Query selectQuery =
                currentSession.createQuery("FROM FuelCosts F where F.theUser.id=:userId").setParameter("userId", userId);
        selectQuery.setFirstResult((pageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);
        List fuelList = selectQuery.getResultList();
        executeAndCloseTransaction(currentSession);
        return fuelList;
    }
}