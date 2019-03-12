package com.infoshare.lumato.services;

import com.infoshare.lumato.beans.FuelInputBean;
import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.dao.FuelCostsDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
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
        List<FuelCosts> fuelCostListByCarId = new ArrayList<>();
        for (FuelCosts fuelCosts : fuelInputBean.getFuelCostsList()) {
            if (fuelCosts.getIdCar() == fuelInputBean.getCar().getCarId()) {
                fuelCostListByCarId.add(fuelCosts);
            }
        }
        return fuelCostListByCarId;
    }

    public boolean isMileageCorrect(FuelCosts fuelCosts, Calendar calendar) {

        fuelCosts.setDate(calendar);
        List<FuelCosts> fuelCostListByCarId = getFuelCostListByCarId();
        fuelCostListByCarId.add(fuelCosts);
        fuelCostListByCarId.sort(new FuelCostComparatorByDate());

        for (int i = 1; i < fuelCostListByCarId.size() - 2; i++) {
            if (fuelCostListByCarId.get(i).getCurrentMileage() > fuelCostListByCarId.get(i + 1).getCurrentMileage())
                return false;
        }
        return true;
    }
}