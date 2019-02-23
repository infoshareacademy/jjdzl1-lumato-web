package com.infoshare.lumato.services;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.persistence.DBConnection;
import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
public class CarsService {

    private List<Car> cars = new ArrayList<>();

    @Inject
    DBConnection myConn;


    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    public List<Car> getAllCarsByUser() {
        try {
            String sql = "SELECT cars.idcars, cars.brand, cars.model, cars.year, cars.fuelType, cars.comment " +
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
                String comment = myResults.getString("comment");

                Car tempCar = new Car(carId, currentUser.getUserId(), brand, model, year, fuelType, comment);
                cars.add(tempCar);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cars;
    }

    public void addCar(Car theCar) {
        try {
            String sql = "insert into cars (brand, model, year, fuelType, comment, iduser) values (?,?,?,?,?,?)";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);

            myStmt.setString(1, theCar.getBrand());
            myStmt.setString(2, theCar.getModel());
            myStmt.setInt(3, theCar.getProductionYear());
            myStmt.setString(4, theCar.getFuelType());
            myStmt.setString(5, theCar.getAdditionalInfo());
            myStmt.setInt(6, currentUser.getUserId());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCar(Car theCar) {
        try {
            String sql = "DELETE FROM cars WHERE idcar=?";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, theCar.getCarId());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}









































