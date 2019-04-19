package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

@RequestScoped
@Named
public class CarDAO extends CommonDAO {

    private final User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private final int userId = currentUser.getUserId();

    public List<Car> getAllCarsByUser() {
        Session currentSession = getSession();
        String hQuery = "FROM Car C WHERE C.theUser.id=:userId";
        Query<Car> query = currentSession.createQuery(hQuery, Car.class).setParameter("userId", userId);
        List<Car> cars = query.getResultList();
        executeAndCloseTransaction(currentSession);
        return cars;
    }

    public void addOrUpdateCar(Car theCar) {
        Session currentSession = getSession();
        User tempUser = currentSession.get(User.class, userId);
        tempUser.addCar(theCar);
        currentSession.saveOrUpdate(theCar);
        executeAndCloseTransaction(currentSession);
    }

    public void deleteCar(Car theCar) {
        Session currentSession = getSession();
        currentSession.delete(theCar);
        executeAndCloseTransaction(currentSession);
    }

    public Car findCarByRegistrationPlate(String regPlate) {
        Car carInDB = null;
        Session currentSession = getSession();
        try {
            String hQuery = "FROM Car C WHERE C.regPlate=:regPlate";
            carInDB = currentSession.createQuery(hQuery, Car.class).setParameter("regPlate", regPlate).getSingleResult();
        } catch (NoResultException ignored) {
        }
        executeAndCloseTransaction(currentSession);
        return carInDB;
    }

    public Car findCarById(int id) {
        Car carInDB = null;
        Session currentSession = getSession();
        try {
            String hQuery = "FROM Car C WHERE C.id=:id";
            carInDB = currentSession.createQuery(hQuery, Car.class).setParameter("id", id).getSingleResult();
        } catch (NoResultException ignored) {
        }
        executeAndCloseTransaction(currentSession);
        return carInDB;
    }

    public List getCarsPerPage(int pageNumber, int pageSize) {
        Session currentSession = getSession();
        Query selectQuery =
                currentSession.createQuery("FROM Car C where C.theUser.id=:userId").setParameter("userId", userId);
        selectQuery.setFirstResult((pageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);
        List carList = selectQuery.getResultList();
        executeAndCloseTransaction(currentSession);
        return carList;
    }

    private Long countCarsByUser() {
        Session currentSession = getSession();
        String countQ =
                "select count (C.id) from Car C where C.theUser.id=:userId";
        Query countQuery =
                currentSession.createQuery(countQ).setParameter("userId", userId);
        Long numberOfCars = (Long) countQuery.uniqueResult();
        executeAndCloseTransaction(currentSession);
        return numberOfCars;
    }

    public int getNumberOfPages(int pageSize) {
        double numberOfPages = Math.ceil(countCarsByUser() / pageSize);
        return countCarsByUser() % pageSize != 0 ? (int) numberOfPages + 1 : (int) numberOfPages;
    }


}