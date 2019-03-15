package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.FuelCostsTableDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.utils.SortOrder;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Named
@ViewScoped
public class FuelTableService implements Serializable {

    private static final long serialVersionUID = -8569464224056311242L;

    @Inject
    FuelCostsTableDAO repository;

    // TODO: 15.03.2019 zamienic na car.getCarList
    public List<Car> getCarList(){
        return this.repository.getCarList();
    }

    public List<FuelCosts> getFuelCostsList(){
        return this.repository.getFuelCostsList();
    }

    public void sortListByOrder(List<FuelCosts> fuelCostsListFiltered, SortOrder sortOrder){
        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(fuelCostsListFiltered, Comparator.comparing(FuelCosts::getDate));
        } else {
            Collections.sort(fuelCostsListFiltered, Comparator.comparing(FuelCosts::getDate).reversed());
        }
    }

    public String getCarAsString(Integer idOfCar) {
        Car car = getCarList().stream()
                .filter(c -> c.getCarId() == idOfCar)
                .findFirst()
                .get();
        return car.getBrand() + " " + car.getModel() + " [" + car.getRegPlate() + "]";
    }

}
