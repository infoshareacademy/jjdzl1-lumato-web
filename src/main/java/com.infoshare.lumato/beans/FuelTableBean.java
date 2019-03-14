package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;
import com.infoshare.lumato.utils.SortOrder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ViewScoped
@Named("fuelTableBean")
public class FuelTableBean implements Serializable {

    @Inject
    private FuelsCostsService fuelsCostsService;

    private List<FuelCosts> fuelCostsList;

    private List<FuelCosts> fuelCostsListFiltered;

    @Getter
    private List<Car> cars;

    private SortOrder sortOrder;

    private Optional<Integer> idOfCarFilter;

    @PostConstruct
    public void construct() {
        loadFuelCostList();
        loadCars();
        this.sortOrder = SortOrder.DESC;
        this.idOfCarFilter = Optional.empty();
        initializeFuelCostListFiltered();
        filterAndSortList();
    }

    private void loadFuelCostList() {
        fuelCostsList = fuelsCostsService.getAllFuelCostsByUser();
    }

    private void loadCars() {
        cars = fuelsCostsService.getAllCarsByUser();
    }

    private void initializeFuelCostListFiltered(){
        fuelCostsListFiltered = fuelCostsList.stream()
                .sorted(new FuelCostComparatorByDate())
                .collect(Collectors.toList());
    }

    public List<FuelCosts> getFuelCostsListFiltered() {
        filterAndSortList();
        return fuelCostsListFiltered;
    }

    public void filterAndSortList(){
        filterListByCar();
        sortListByOrder();
    }

    public void sortListByOrder(){
        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(this.fuelCostsListFiltered, Comparator.comparing(FuelCosts::getDate));
        } else {
            Collections.sort(this.fuelCostsListFiltered, Comparator.comparing(FuelCosts::getDate).reversed());
        }
    }

    public void filterListByCar(){
        if (!idOfCarFilter.isPresent()) {
            initializeFuelCostListFiltered();
        } else {
            fuelCostsListFiltered = fuelCostsList.stream()
                    .filter(record -> record.getIdCar() == idOfCarFilter.get())
                    .collect(Collectors.toList());
        }
    }

    public String getGetCarAsString(Integer idOfCar) {
        Car car = cars.stream()
                        .filter(c -> c.getCarId() == idOfCar)
                        .findFirst()
                        .get();
        return car.getBrand() + " " + car.getModel() + " [" + car.getRegPlate() + "]";
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