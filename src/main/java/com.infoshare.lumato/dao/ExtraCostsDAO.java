package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
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
public class ExtraCostsDAO extends CommonDAO {
    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private List<ExtraCosts> extraCostsList = new ArrayList<>();


    public void addExtraCostsByCarId(ExtraCosts extraCosts, Car tempCar) {
        try {
            String sql = "INSERT into extracosts (date,idextraCosts, cost, description, idcars) values (?,?,?,?,?)";

            Calendar calendar = extraCosts.getDate();
            java.sql.Date sqlDate = new java.sql.Date(calendar.getTimeInMillis());

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setDate(1, sqlDate);
            myStmt.setInt(2, extraCosts.getId());
            myStmt.setDouble(3, extraCosts.getCosts());
            myStmt.setString(4, extraCosts.getDescription());
            myStmt.setInt(5, extraCosts.getCarId());

            myStmt.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ExtraCosts> getAllExtraCostsByUser() {


        try {
            String sql = "select extracosts.idextraCosts, extracosts.date , extracosts.costs, extracots.amountofextras, extracosts.idcars\n" +
                    "from extacosts, cars, users \n" +
                    "where users.iduser=cars.iduser \n" +
                    "and cars.idcars=extracosts.idcar \n" +
                    "and users.iduser=(?)";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, currentUser.getUserId());
            ResultSet resultSet = myStmt.executeQuery();

            while (resultSet.next()) {
                GregorianCalendar myCal = new GregorianCalendar();
                ExtraCosts tempExtraCost = new ExtraCosts();
                tempExtraCost.setId(resultSet.getInt("idextracost"));
                tempExtraCost.setCosts(resultSet.getDouble("cost"));
                tempExtraCost.setDescription(resultSet.getString("descryption"));

               //TODO
              //  tempExtraCost.getCarId(resultSet.getInt( ? ?))

                Date date = resultSet.getDate("date");
                myCal.setTime(date);

                tempExtraCost.setDate(myCal);

                extraCostsList.add(tempExtraCost);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extraCostsList;
    }

    public void deleteExtraCosts(ExtraCosts extraCosts) {
        try {
            String sql = "DELETE FROM extracosts WHERE idextraCosts=?";

            PreparedStatement myStmt = myConn.getConnection().prepareStatement(sql);
            myStmt.setInt(1, extraCosts.getId());
            myStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
