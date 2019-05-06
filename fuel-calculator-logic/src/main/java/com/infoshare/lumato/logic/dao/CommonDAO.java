package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.logic.persistence.HibernateConfig;
import com.infoshare.lumato.logic.utils.HttpUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.List;

public class CommonDAO {

    @Inject
    HibernateConfig hibernateConfig;

    User currentUser = (User) HttpUtils.getSession().getAttribute("currentUser");

    public Session getSession() {
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

    public List<Object> getAllItemsByUser(Class c) {
        Session currentSession = getSession();
        String hQuery = "FROM " + c.getSimpleName() + " T where T.theUser.id=:userId";
        Query query = currentSession.createQuery(hQuery, Car.class).setParameter("userId", currentUser.getUserId());
        List resultList = query.getResultList();
        executeAndCloseTransaction(currentSession);
        return resultList;
    }

    public int getNumberOfPages(Class c, int pageSize) {
        double numberOfPages = Math.ceil(countObjectsByUser(c) / pageSize);
        return countObjectsByUser(c) % pageSize != 0 ? (int) numberOfPages + 1 : (int) numberOfPages;
    }

    public void executeAndCloseTransaction(Session currentSession) {
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public List<Object> getItemsPerPage(int pageNumber, int pageSize, Class c) {
        Session currentSession = getSession();
        Query selectQuery =
                currentSession.createQuery("FROM " + c.getSimpleName() + " T where T.theUser.id=:userId").setParameter("userId", currentUser.getUserId());
        selectQuery.setFirstResult((pageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);
        List resultList = selectQuery.getResultList();
        executeAndCloseTransaction(currentSession);
        return resultList;
    }
}