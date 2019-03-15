package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HttpUtils;

import javax.enterprise.context.RequestScoped;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;


@RequestScoped
public class ExtraCostDao extends CommonDAO {

    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private List<ExtraCosts> extraCostsList = new ArrayList<>();

    public void addExtraCostByCarId(ExtraCosts extraCosts, Car car) {
        try {
            String sql = "INSERT into extracosts (cost, description, idcars, costdate) values (?,?,?,?)";

            Calendar calendar = extraCosts.getDate();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setDouble(1, extraCosts.getCost());
            myStmt.setString(2, extraCosts.getDescription());
            myStmt.setInt(3, car.getCarId());
            myStmt.setDate(4, sqlDate);

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ExtraCosts> getAllExtraCostsByUser() {

        try {
            String sql = "SELECT extracosts.idextracosts, extracosts.cost, extracosts.description, extracosts.idcars, extracosts.costdate " +
                    "FROM extracosts, cars, users " +
                    "WHERE users.iduser=cars.iduser " +
                    "AND cars.idcars=extracosts.idcars " +
                    "AND users.iduser=(?)";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, currentUser.getUserId());
            ResultSet resultSet = myStmt.executeQuery();

            while (resultSet.next()) {
                GregorianCalendar myCal = new GregorianCalendar();
                ExtraCosts tempExtraCosts = new ExtraCosts();
                tempExtraCosts.setId(resultSet.getInt("idextracosts"));
                tempExtraCosts.setCost(resultSet.getDouble("cost"));
                tempExtraCosts.setDescription(resultSet.getString("description"));
                tempExtraCosts.setCarId(resultSet.getInt("idcars"));

                Date date = resultSet.getDate("costdate");
                myCal.setTime(date);
                tempExtraCosts.setDate(myCal);
                extraCostsList.add(tempExtraCosts);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extraCostsList;
    }

    public void deleteExtraCost(ExtraCosts extraCosts) {
        try {
            String sql = "DELETE FROM extracosts WHERE idextracosts=?";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, extraCosts.getId());
            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
