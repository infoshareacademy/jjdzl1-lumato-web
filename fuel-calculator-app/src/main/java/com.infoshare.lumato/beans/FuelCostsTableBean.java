package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.dao.CarDAO;
import com.infoshare.lumato.logic.dao.FuelCostsDAO;
import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.FuelCosts;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FuelCostsTableBean implements Serializable {

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
        this.carList = this.carDAO.getAllItemsByUser(Car.class, HttpUtils.getCurrentUserFromSession().getUserId());
        this.fuelCostsList = this.fuelCostsDAO.getAllItemsByUser(FuelCosts.class, HttpUtils.getCurrentUserFromSession().getUserId());
    }


}
