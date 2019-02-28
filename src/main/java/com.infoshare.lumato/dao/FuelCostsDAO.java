package com.infoshare.lumato.dao;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Named
@RequestScoped
public class FuelCostsDAO extends CommonDAO {

    public double calculateAverageFuelCost(String fuelType){
        double averageFuelCost = 0;
        try {
            String sql = "SELECT AVG(priceperliter) AS averageFuelCost FROM fuelcosts WHERE typeoffuel=?";
            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setString(1, fuelType);
            ResultSet resultSet = myStmt.executeQuery();
            resultSet.next();
            averageFuelCost = resultSet.getInt("averageFuelCost");
        } catch (Exception exc) {
            System.out.println("Cannot count average fuel cost!");
            exc.printStackTrace();
        }
        return averageFuelCost;
    }
}
