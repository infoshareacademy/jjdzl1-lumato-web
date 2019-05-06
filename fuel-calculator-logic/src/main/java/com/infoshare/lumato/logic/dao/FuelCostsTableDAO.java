package com.infoshare.lumato.logic.dao;


import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.FuelCosts;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FuelCostsTableDAO implements Serializable {

    private static final long serialVersionUID = 6256489859167556819L;

    private List<Object> fuelCostsList;

    private List<Object> carList;

    public List<Object> getFuelCostsList() {
        return fuelCostsList;
    }

    public List<Object> getCarList() {
        return carList;
    }

    @Inject
    FuelCostsDAO fuelCostsDAO;

    @Inject
    CarDAO carDAO;

    @PostConstruct
    public void construct() {
        this.carList = this.carDAO.getAllItemsByUser(Car.class);
        this.fuelCostsList = this.fuelCostsDAO.getAllItemsByUser(FuelCosts.class);
    }

}
