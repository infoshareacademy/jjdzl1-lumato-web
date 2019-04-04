package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.utils.HibernateConfig;
import com.infoshare.lumato.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class CarDAO extends CommonDAO {

    public CarDAO(){}

    private final SessionFactory sessionFactory = HibernateConfig.getSessionFactory();



    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private List<Car> cars = new ArrayList<>();
    int userId = currentUser.getUserId();



    public List<Car> getAllCarsByUser() {

        Session currentSession  = sessionFactory.openSession();
        currentSession.beginTransaction();

        String hQuery = "FROM Car C WHERE C.idUserInCars=:userId";

        Query<Car> query = currentSession.createQuery(hQuery, Car.class).setParameter("userId", userId);

        List<Car> cars = query.getResultList();


        currentSession.getTransaction().commit();
        currentSession.close();

        return cars;
    }

    public void addCar(Car theCar) {
        /*try {
            String sql = "insert into cars (brand, model, year, fuelType, regplate, iduser) values (?,?,?,?,?,?)";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);

            myStmt.setString(1, theCar.getBrand());
            myStmt.setString(2, theCar.getModel());
            myStmt.setInt(3, theCar.getProductionYear());
            myStmt.setString(4, theCar.getFuelType());
            myStmt.setString(5, theCar.getRegPlate());
            myStmt.setInt(6, currentUser.getUserId());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        Session currentSession  = sessionFactory.openSession();
        currentSession.beginTransaction();


        currentSession.saveOrUpdate(theCar);


        currentSession.getTransaction().commit();
        currentSession.close();



    }

    public void deleteCar(Car theCar) {
        try {
            String sql = "DELETE FROM cars WHERE idcars=?";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, theCar.getCarId());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Car findCarByRegistrationPlate(String regPlate) {
        Car carInDB = new Car();
        try {
            String sql = "SELECT * FROM cars WHERE regplate = ?";
            PreparedStatement statement = myConn.getConnection().prepareStatement(sql);
            statement.setString(1, regPlate);
            ResultSet resultSet = statement.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                carInDB = null;
            } else {
                resultSet.next();
                fillCarData(carInDB, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public void updateCar(Car carInDB) {

        try {
            String sql = "update cars set model=?, brand=?, year=?, fueltype=?, regplate=? where idcars=?";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);

            myStmt.setString(1, carInDB.getModel());
            myStmt.setString(2, carInDB.getBrand());
            myStmt.setInt(3, carInDB.getProductionYear());
            myStmt.setString(4, carInDB.getFuelType());
            myStmt.setString(5, carInDB.getRegPlate());
            myStmt.setInt(6, carInDB.getCarId());
            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    
    private void fillCarData(Car theCar, ResultSet resultSet) throws SQLException {
        theCar.setCarId(resultSet.getInt("idcars"));
        theCar.setIdUserInCars(resultSet.getInt("iduser"));
        theCar.setBrand(resultSet.getString("brand"));
        theCar.setModel(resultSet.getString("model"));
        theCar.setFuelType(resultSet.getString("fueltype"));
        theCar.setRegPlate(resultSet.getString("regplate"));
        theCar.setProductionYear(resultSet.getInt("year"));
    }


}