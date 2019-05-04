package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.persistence.HibernateConfig;
import com.infoshare.lumato.logic.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Inject;

public class CommonDAO {

    User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    @Inject
    HibernateConfig hibernateConfig;

    Session getSession() {
        Session currentSession = hibernateConfig.getSessionFactory().openSession();
        currentSession.beginTransaction();
        return currentSession;
    }

    public void deleteObject(Object o) {
        Session currentSession = getSession();
        currentSession.delete(o);
        executeAndCloseTransaction(currentSession);
    }

    private Long countObjectsByUser(Class o) {
        Session currentSession = getSession();
        String countQ =
                "select count (T.id) from " + o.getSimpleName() + " T where T.theUser.id=:userId";
        Query countQuery =
                currentSession.createQuery(countQ).setParameter("userId", currentUser.getUserId());
        Long numberOfCars = (Long) countQuery.uniqueResult();
        executeAndCloseTransaction(currentSession);
        return numberOfCars;
    }

    public int countAllRecords(Class entityClass) {
        int amountOfRecords = 0;
        Session currentSession = getSession();
        String SQL_QUERY = "select count(*) from " + entityClass.getSimpleName();
        amountOfRecords = ((Long) currentSession.createQuery(SQL_QUERY).uniqueResult()).intValue();
        executeAndCloseTransaction(currentSession);
        return amountOfRecords;
    }

    public int getNumberOfPages(Class o, int pageSize) {
        double numberOfPages = Math.ceil(countObjectsByUser(o) / pageSize);
        return countObjectsByUser(o) % pageSize != 0 ? (int) numberOfPages + 1 : (int) numberOfPages;
    }

    void executeAndCloseTransaction(Session currentSession) {
        currentSession.getTransaction().commit();
        currentSession.close();
    }



}