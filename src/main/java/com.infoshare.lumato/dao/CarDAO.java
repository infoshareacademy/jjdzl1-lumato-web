package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HibernateConfig;
import com.infoshare.lumato.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.NoResultException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RequestScoped
@Named
public class CarDAO extends CommonDAO {

    public CarDAO() {
    }

    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();

    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private int userId = currentUser.getUserId();


    private Session getSession() {
        Session currentSession = sessionFactory.openSession();
        currentSession.beginTransaction();
        return currentSession;
    }

    private void executeAndCloseTransaction(Session currentSession) {
        currentSession.getTransaction().commit();
        currentSession.close();
    }

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
    // TODO: 06.04.2019

    public Car findCarByRegistrationPlate(String regPlate) {
        Car carInDB = null;
        Session currentSession = getSession();

        try {
            String hQuery = "FROM Car C WHERE C.regPlate=:regPlate";
            Query<Car> query = currentSession.createQuery(hQuery, Car.class).setParameter("regPlate", regPlate);
            carInDB = currentSession.createQuery(hQuery, Car.class).setParameter("regPlate", regPlate).getSingleResult();
        } catch (NoResultException ignored) {
        }

        executeAndCloseTransaction(currentSession);

        return carInDB;
    }

    public Car findCarById(int id) {
        Car carInDB = new Car();
        try {
            String sql = "SELECT * FROM cars WHERE idcars = ?";
            PreparedStatement statement = myConn.getConnection().prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return null;
            } else {
                resultSet.next();
                fillCarData(carInDB, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carInDB;
    }

    private void fillCarData(Car theCar, ResultSet resultSet) throws SQLException {
        theCar.setCarId(resultSet.getInt("idcars"));
        theCar.getUser().setUserId(resultSet.getInt("iduser"));
        theCar.setBrand(resultSet.getString("brand"));
        theCar.setModel(resultSet.getString("model"));
        theCar.setFuelType(resultSet.getString("fueltype"));
        theCar.setRegPlate(resultSet.getString("regplate"));
        theCar.setProductionYear(resultSet.getInt("year"));
    }

}