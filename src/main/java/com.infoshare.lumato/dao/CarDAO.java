package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RequestScoped
@Named
public class CarDAO {

    @Inject
    DBConnection myConn;

    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private List<Car> cars = new ArrayList<>();

    public List<Car> getAllCarsByUser() {
        try {
            String sql = "SELECT cars.idcars, cars.brand, cars.model, cars.year, cars.fuelType, cars.regplate " +
                    "FROM lumato.cars, lumato.users WHERE users.iduser=cars.iduser " +
                    "AND users.iduser=" + currentUser.getUserId();

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            ResultSet myResults = myStmt.executeQuery(sql);

            while (myResults.next()) {
                int carId = myResults.getInt("idcars");
                String brand = myResults.getString("brand");
                String model = myResults.getString("model");
                int year = myResults.getInt("year");
                String fuelType = myResults.getString("fuelType");
                String regPlate = myResults.getString("regplate");

                Car tempCar = new Car(carId, currentUser.getUserId(), brand, model, year, fuelType, regPlate);
                cars.add(tempCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void addCar(Car theCar) {
        try {
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
        }
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

/*
    public Car loadCarById(int carId) {
        Car carInDB = new Car();

        try {
            String sql = "select * from cars where idcars=?";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, carId);
            ResultSet resultSet = myStmt.executeQuery();

            while (resultSet.next()) {
                fillCarData(carInDB, resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return carInDB;
    }
*/

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