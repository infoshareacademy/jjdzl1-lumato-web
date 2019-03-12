package com.infoshare.lumato.services;

import com.infoshare.lumato.beans.FuelInputBean;
import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.dao.FuelCostsDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequestScoped
public class FuelsCostsService {

    @Inject
    FuelCostsDAO fuelCostsDAO;

    @Inject
    CarDAO carDAO;

    @Inject
    FuelInputBean fuelInputBean;

    public void addFuelCost(FuelCosts fuelCosts, Car car) {
        fuelCostsDAO.addFuelCostByCarId(fuelCosts, car);
    }

    public List<Car> getAllCarsByUser() {
        return carDAO.getAllCarsByUser();
    }

    public List<FuelCosts> getAllFuelCostsByUser() {
        return fuelCostsDAO.getAllFuelCostByUser();
    }

    public boolean isFuelAmountAndPriceNotEmpty(FuelCosts fuelCosts) {
        return (fuelCosts.getAmountOfFuel() > 0 & fuelCosts.getPricePerLiter() > 0);
    }

    public List<FuelCosts> buildFuelCostListByCarId() {

        List<FuelCosts> tempFuelCostList = new ArrayList<>();

        for (FuelCosts fuelCosts : fuelInputBean.loadFuelCostList()) {
            if (fuelCosts.getIdCar() == fuelInputBean.getCar().getCarId()) {
                tempFuelCostList.add(fuelCosts);
            }
        }
        return tempFuelCostList;
    }


}
