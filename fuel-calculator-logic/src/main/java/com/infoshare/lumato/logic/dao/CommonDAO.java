package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.persistence.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.util.List;

public class CommonDAO {

    @Inject
    HibernateConfig hibernateConfig;

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

    private Long countObjectsByUser(Class o, int userId) {
        Session currentSession = getSession();
        String countQ =
                "select count (T.id) from " + o.getSimpleName() + " T where T.theUser.id=:userId";
        Query countQuery =
                currentSession.createQuery(countQ).setParameter("userId", userId);
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

    public List<Object> getAllItemsByUser(Class c, int userId) {
        Session currentSession = getSession();
        String hQuery = "FROM " + c.getSimpleName() + " T where T.theUser.id=:userId";
        Query query = currentSession.createQuery(hQuery, Car.class).setParameter("userId", userId);
        List resultList = query.getResultList();
        executeAndCloseTransaction(currentSession);
        return resultList;
    }

    public int getNumberOfPages(Class c, int pageSize, int userId) {
        double numberOfPages = Math.ceil(countObjectsByUser(c, userId) / pageSize);
        return countObjectsByUser(c, userId) % pageSize != 0 ? (int) numberOfPages + 1 : (int) numberOfPages;
    }

    public void executeAndCloseTransaction(Session currentSession) {
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public List<Object> getItemsPerPage(int pageNumber, int pageSize, Class c, int userId) {
        Session currentSession = getSession();
        Query selectQuery =
                currentSession.createQuery("FROM " + c.getSimpleName() + " T where T.theUser.id=:userId").setParameter("userId", userId);
        selectQuery.setFirstResult((pageNumber - 1) * pageSize);
        selectQuery.setMaxResults(pageSize);
        List resultList = selectQuery.getResultList();
        executeAndCloseTransaction(currentSession);
        return resultList;
    }
}