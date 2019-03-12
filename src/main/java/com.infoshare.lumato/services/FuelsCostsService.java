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

    private List<FuelCosts> getFuelCostListByCarId() {

        List<FuelCosts> tempFuelCostList = new ArrayList<>();
        List<FuelCosts> list = fuelInputBean.getFuelCostsList();
        for (FuelCosts fuelCosts : list) {
            if (fuelCosts.getIdCar() == fuelInputBean.getCar().getCarId()) {
                tempFuelCostList.add(fuelCosts);
            }
        }
        return tempFuelCostList;
    }

    public boolean isMileageCorrect(FuelCosts fuelCosts){
        List<FuelCosts> fuelCostListByCarId = getFuelCostListByCarId();
        Collections.sort(fuelCostListByCarId);
        FuelCosts lastFuelCost = fuelCostListByCarId.get(fuelCostListByCarId.size()-1);
        return fuelCosts.getCurrentMileage() > lastFuelCost.getCurrentMileage();

    }
}
