package com.infoshare.lumato.dao;

import com.infoshare.lumato.persistence.DBConnection;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class FuelCostsDAO {

    @Inject
    DBConnection myConn;

}
