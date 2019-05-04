package com.infoshare.lumato.logic.dao;


import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;
import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.List;

@Named
@RequestScoped
public class ExtraCostDao extends CommonDAO {

    //    private final User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");
    //    delete
    //    countObjectsByUser
    //    getNumberOfPages

    private final int userId = currentUser.getUserId();

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

    public List getItemsPerPage(int pageNumber, int pageSize) {
        Session currentSession = getSession();
        Query selectQuery =
                currentSession.createQuery("FROM ExtraCosts E where E.theUser.id=:userId").setParameter("userId", userId);
        selectQuery.setFirstResult((pageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);
        List extraCostList = selectQuery.getResultList();
        executeAndCloseTransaction(currentSession);
        return extraCostList;
    }
}