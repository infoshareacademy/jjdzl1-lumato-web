package com.infoshare.lumato.logic.dao;


import com.infoshare.lumato.logic.persistence.DBConnection;
import com.infoshare.lumato.logic.persistence.HibernateConfig;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Iterator;


public abstract class CommonDAO {

    @Inject
    DBConnection myConn;

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
        String tableName = entityClass.getSimpleName();
        String SQL_QUERY = "SELECT COUNT(*) FROM " + tableName;
        amountOfRecords = ((Long)getSession().createQuery(SQL_QUERY).uniqueResult()).intValue();
        executeAndCloseTransaction(currentSession);
        return amountOfRecords;
    }

}
