package com.infoshare.lumato.logic.dao;


import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.util.List;

@RequestScoped
@Named
public class CarDAO extends CommonDAO {

    public void addOrUpdateCar(Car theCar) {
        Session currentSession = getSession();
        User tempUser = currentSession.get(User.class, currentUser.getUserId());
        tempUser.addCar(theCar);
        currentSession.saveOrUpdate(theCar);
        executeAndCloseTransaction(currentSession);
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
}