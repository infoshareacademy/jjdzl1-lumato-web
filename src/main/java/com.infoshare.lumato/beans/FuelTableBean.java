package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.FuelTableService;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;
import com.infoshare.lumato.utils.SortOrder;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ViewScoped
@Named("fuelTableBean")
public class FuelTableBean implements Serializable {

    private static final long serialVersionUID = 1709172815224096384L;

    @Inject
    FuelTableService fuelTableService;

    private List<FuelCosts> fuelCostsListFiltered;

    private SortOrder sortOrder;

    private Optional<Integer> idOfCarFilter;

    @PostConstruct
    public void construct() {
        this.sortOrder = SortOrder.DESC;
        this.idOfCarFilter = Optional.empty();
        initializeFuelCostListFiltered();
        filterAndSortList();
    }

    public List<Car> getCarList(){
        return fuelTableService.getCarList();
    }

    private void initializeFuelCostListFiltered(){
        fuelCostsListFiltered = fuelTableService.getFuelCostsList().stream()
                .sorted(new FuelCostComparatorByDate())
                .collect(Collectors.toList());
    }

    public List<FuelCosts> getFuelCostsListFiltered() {
        filterAndSortList();
        return fuelCostsListFiltered;
    }

    public void filterAndSortList(){
        filterListByCar();
        fuelTableService.sortListByOrder(fuelCostsListFiltered, sortOrder);
    }

    public void filterListByCar(){
        if (!idOfCarFilter.isPresent()) {
            initializeFuelCostListFiltered();
        } else {
            fuelCostsListFiltered = fuelTableService.getFuelCostsList().stream()
                    .filter(record -> record.getIdCar() == idOfCarFilter.get())
                    .collect(Collectors.toList());
        }
    }

    public String getCarAsString(Integer idOfCar){
        return fuelTableService.getCarAsString(idOfCar);
    }

    public void setSortAscending(){
        this.sortOrder = SortOrder.ASC;
    }

    public void setSortDescending(){
        this.sortOrder = SortOrder.DESC;
    }

    public void setAllCarsFilter() {
        this.idOfCarFilter = Optional.empty();
    }

    public void setSingleCarFilter(int id) {
        this.idOfCarFilter = Optional.of(id);
    }
}