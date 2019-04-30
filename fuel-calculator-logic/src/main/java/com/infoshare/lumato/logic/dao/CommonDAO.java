package com.infoshare.lumato.logic.dao;


import com.infoshare.lumato.logic.persistence.DBConnection;
import com.infoshare.lumato.logic.persistence.HibernateConfig;
import org.hibernate.Session;

import javax.inject.Inject;
import java.sql.ResultSet;
import java.sql.Statement;


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

    public int countAllRecords(String tableName) {
        int amountOfUsers = 0;
        try {
            String sql = "SELECT COUNT(*) AS carsAmount FROM " + tableName;
            Statement myStmt = myConn.getConnection().prepareStatement(sql);
            ResultSet resultSet = myStmt.executeQuery(sql);
            resultSet.next();
            amountOfUsers = resultSet.getInt("carsAmount");
        } catch (Exception exc) {
            System.out.println("Cannot count records!");
            exc.printStackTrace();
        }
        return amountOfUsers;
    }

}
