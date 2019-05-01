package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.logic.persistence.HibernateConfig;
import org.hibernate.Session;

import javax.inject.Inject;

public abstract class CommonDAO {

    @Inject
    HibernateConfig hibernateConfig;

    Session getSession() {
        Session currentSession = hibernateConfig.getSessionFactory().openSession();
        currentSession.beginTransaction();
        return currentSession;
    }

    void executeAndCloseTransaction(Session currentSession) {
        currentSession.getTransaction().commit();
        currentSession.close();
    }

    public int countAllRecords(Class entityClass) {
        int amountOfRecords = 0;
        Session currentSession = getSession();
        String SQL_QUERY = "select count(*) from " + entityClass.getSimpleName();
        amountOfRecords = ((Long)currentSession.createQuery(SQL_QUERY).uniqueResult()).intValue();
        executeAndCloseTransaction(currentSession);
        return amountOfRecords;
    }

}
