package com.infoshare.lumato.services;

import com.infoshare.lumato.beans.FuelInputBean;
import com.infoshare.lumato.logic.dao.FuelCostsDAO;
import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.FuelCosts;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@ViewScoped
public class FuelsCostsService extends PaginationService implements Serializable {

    @Inject
    FuelCostsDAO fuelCostsDAO;

    @Inject
    FuelInputBean fuelInputBean;

    public void addFuelCost(FuelCosts fuelCosts, Car car) {
        fuelCostsDAO.addFuelCostByCarId(fuelCosts, car);
    }

    public List<FuelCosts> getAllFuelCostsByUser() {
        return fuelCostsDAO.getAllFuelCostByUser();
    }

    public boolean isFuelAmountAndPriceNotEmpty(FuelCosts fuelCosts) {
        return (fuelCosts.getAmountOfFuel() > 0 & fuelCosts.getPricePerLiter() > 0);
    }

    private List<FuelCosts> getFuelCostListByCarId() {
        List<FuelCosts> fuelCostListByCarId = new ArrayList<>();
        for (FuelCosts fuelCosts : fuelInputBean.getCompleteFuelCostsList()) {
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

    public void deleteFuelCost(FuelCosts fuelCosts) {
        fuelCostsDAO.deleteFuelCost(fuelCosts);
    }


    @Override
    public void firstPage() {
        super.firstPage();
    }

    @Override
    public void previousPage() {
        super.previousPage();
    }

    @Override
    public void nextPage() {
        super.nextPage();
    }

    @Override
    public void lastPage() {
        super.lastPage();
    }

    @Override
    public int getNumberOfPages() {
        return fuelCostsDAO.getNumberOfPages(itemsOnPage);
    }

    @Override
    public List getCurrentItemsList() {
        return fuelCostsDAO.getItemsPerPage(page, itemsOnPage);
    }

    @Override
    public void setItemsOnPage(int itemsOnPage) {
        super.setItemsOnPage(itemsOnPage);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}