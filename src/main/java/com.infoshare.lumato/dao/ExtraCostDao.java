package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
public class ExtraCostDao extends CommonDAO {

    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private int userId = currentUser.getUserId();

    private List<ExtraCosts> extraCostsList = new ArrayList<>();

    public void addExtraCostByCarId(ExtraCosts extraCosts, Car car) {
        /*try {
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
        }*/


        Session currentSession = getSession();
        User tempUser = currentSession.get(User.class, userId);
        tempUser.addExtraCost(extraCosts);
        car.addExtraCost(extraCosts);
        executeAndCloseTransaction(currentSession);

    }

    public List<ExtraCosts> getAllExtraCostsByUser() {
        Session currentSession = getSession();

        String hQuery = "FROM ExtraCosts E WHERE E.theUser.id=:userId";
        Query<ExtraCosts> query = currentSession.createQuery(hQuery, ExtraCosts.class).setParameter("userId", userId);
        List<ExtraCosts> extraCostsList = query.getResultList();
        executeAndCloseTransaction(currentSession);
        return extraCostsList;
    }

    public void deleteExtraCost(ExtraCosts extraCosts) {
        Session currentSession = getSession();
        currentSession.delete(extraCosts);
        executeAndCloseTransaction(currentSession);
    }
}
