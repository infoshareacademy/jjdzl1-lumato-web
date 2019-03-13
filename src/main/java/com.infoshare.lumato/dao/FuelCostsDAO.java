package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

@Named
@RequestScoped
public class FuelCostsDAO extends CommonDAO {

    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private List<FuelCosts> fuelCostList = new ArrayList<>();

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

    public void addFuelCostByCarId(FuelCosts fuelCosts, Car tempCar) {
        try {
            String sql = "INSERT into fuelcosts (date,priceperliter,amountoffuel,currentmileage,typeoffuel,idcar) values (?,?,?,?,?,?)";

            Calendar calendar = fuelCosts.getDate();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setDate(1, sqlDate);
            myStmt.setDouble(2, fuelCosts.getPricePerLiter());
            myStmt.setDouble(3, fuelCosts.getAmountOfFuel());
            myStmt.setInt(4, fuelCosts.getCurrentMileage());
            myStmt.setString(5, tempCar.getFuelType());
            myStmt.setInt(6, tempCar.getCarId());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<FuelCosts> getAllFuelCostByUser() {


        try {
            String sql = "select fuelcosts.idfuelcost, fuelcosts.date , fuelcosts.priceperliter, fuelcosts.amountoffuel, fuelcosts.currentmileage, fuelcosts.typeoffuel, fuelcosts.idcar\n" +
                    "from fuelcosts, cars, users \n" +
                    "where users.iduser=cars.iduser \n" +
                    "and cars.idcars=fuelcosts.idcar \n" +
                    "and users.iduser=(?)";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, currentUser.getUserId());
            ResultSet resultSet = myStmt.executeQuery();

            while (resultSet.next()) {
                GregorianCalendar myCal = new GregorianCalendar();
                FuelCosts tempFuelCost = new FuelCosts();
                tempFuelCost.setId(resultSet.getInt("idfuelcost"));
                tempFuelCost.setPricePerLiter(resultSet.getDouble("priceperliter"));
                tempFuelCost.setAmountOfFuel(resultSet.getDouble("amountoffuel"));
                tempFuelCost.setCurrentMileage(resultSet.getInt("currentmileage"));
                tempFuelCost.setFuelType(resultSet.getString("typeoffuel"));
                tempFuelCost.setIdCar(resultSet.getInt("idcar"));

                Date date = resultSet.getDate("date");
                myCal.setTime(date);

                tempFuelCost.setDate(myCal);

                fuelCostList.add(tempFuelCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return fuelCostList;
    }

    public void deleteFuelCost(FuelCosts fuelCosts) {
        try {
            String sql = "DELETE FROM fuelCosts WHERE idfuelcost=?";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, fuelCosts.getId());
            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
