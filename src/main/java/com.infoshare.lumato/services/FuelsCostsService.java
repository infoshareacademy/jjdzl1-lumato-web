package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.dao.FuelCostsDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;

@RequestScoped
public class FuelsCostsService {

    @Inject
    FuelCostsDAO fuelCostsDAO;

    @Inject
    CarDAO carDAO;


    public void addFuelCost(FuelCosts fuelCosts, Car car) {
        fuelCostsDAO.addFuelCostByCarId(fuelCosts, car);
    }

    public List<Car> getAllCarsByUser(){
        return carDAO.getAllCarsByUser();
    }


}
