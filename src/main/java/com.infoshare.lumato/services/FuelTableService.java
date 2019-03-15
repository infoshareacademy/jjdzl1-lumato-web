package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.FuelCostsTableDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FuelTableService implements Serializable {

    private static final long serialVersionUID = -8569464224056311242L;

    @Inject
    FuelCostsTableDAO repository;

    public List<Car> getCarList(){
        return this.repository.getCarList();
    }

    public List<FuelCosts> getFuelCostsList(){
        return this.repository.getFuelCostsList();
    }

}
