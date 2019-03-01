package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Named
@RequestScoped
public class FuelCostsDAO extends CommonDAO {

    public double calculateAverageFuelCost(String fuelType) {
        double averageFuelCost = 0;
        try {
            String sql = "SELECT AVG(priceperliter) AS averageFuelCost FROM fuelcosts WHERE typeoffuel=?";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setString(1, fuelType);
            ResultSet resultSet = myStmt.executeQuery();
            resultSet.next();
            averageFuelCost = resultSet.getDouble("averageFuelCost");
        } catch (Exception exc) {
            System.out.println("Cannot count average fuel cost!");
            exc.printStackTrace();
        }
        return averageFuelCost;
    }
    public void addFuelCostByCarId(FuelCosts fuelCosts, Car car) {
        try {
            String sql = "INSERT into fuelcosts (priceperliter, amountoffuel, currentmileage, typeoffuel, idcar,) values (?,?,?,?,?)";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setDouble(1, fuelCosts.getPricePerLiter());
            myStmt.setDouble(2, fuelCosts.getAmountOfFuel());
            myStmt.setInt(3, fuelCosts.getCurrentMileage());
            myStmt.setString(4, car.getFuelType());
            myStmt.setInt(5,car.getCarId());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
