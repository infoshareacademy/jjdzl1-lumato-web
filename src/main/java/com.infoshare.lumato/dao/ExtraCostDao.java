package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
import com.infoshare.lumato.models.User;
import com.infoshare.lumato.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ExtraCostDao extends CommonDAO {

    private User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    private int userId = currentUser.getUserId();

    public void addExtraCostByCarId(ExtraCosts extraCosts, Car car) {
        Session currentSession = getSession();
        User tempUser = currentSession.get(User.class, userId);
        Car tempCar = currentSession.get(Car.class, car.getCarId());
        tempUser.addExtraCost(extraCosts);
        tempCar.addExtraCost(extraCosts);
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