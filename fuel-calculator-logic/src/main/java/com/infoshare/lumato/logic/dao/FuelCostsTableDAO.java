package com.infoshare.lumato.logic.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;

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

    private List<FuelCosts> fuelCostsList;

    private List<Car> carList;

    public List<FuelCosts> getFuelCostsList() {
        return fuelCostsList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    @Inject
    FuelCostsDAO fuelCostsDAO;

    @Inject
    CarDAO carDAO;

    @PostConstruct
    public void construct() {
        this.carList = this.carDAO.getAllCarsByUser();
        this.fuelCostsList = this.fuelCostsDAO.getAllFuelCostByUser();
    }

}
