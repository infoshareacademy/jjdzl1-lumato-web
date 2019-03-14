package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.services.FuelsCostsService;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;
import com.infoshare.lumato.utils.Sort;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ViewScoped
@Named("userDataBean")
public class UserDataBean implements Serializable {

    @Inject
    private FuelsCostsService fuelsCostsService;

    @Inject
    private CarsService carsService;

    private List<FuelCosts> fuelCostsList;

    private List<FuelCosts> fuelCostsListFiltered;

    private List<Car> carList;

    private Sort sort;
    private int idOfCarFilter;

    public List<FuelCosts> getFuelCostsListFiltered() {
        filterAndSortList();
        return fuelCostsListFiltered;
    }

    public void setFuelCostsListFiltered(List<FuelCosts> fuelCostsListFiltered) {
        this.fuelCostsListFiltered = fuelCostsListFiltered;
    }

    public void setFuelCostsList(List<FuelCosts> fuelCostsList) {
        this.fuelCostsList = fuelCostsList;
    }

    public List<FuelCosts> getFuelCostsList() {
        return fuelCostsList;
    }

    public List<Car> getCars() {
        return carList;
    }

    public void setSortAscending(){
        this.sort = Sort.ASC;
    }

    public void setSortDescending(){
        this.sort = Sort.DESC;
    }

    private void loadFuelCostList() {
        fuelCostsList = fuelsCostsService.getAllFuelCostsByUser();
    }

    private void initializeFuelCostListFiltered(){
        fuelCostsListFiltered = fuelCostsList.stream()
                .sorted(new FuelCostComparatorByDate())
                .collect(Collectors.toList());
    }

    private void loadCars() {
        carList = fuelsCostsService.getAllCarsByUser();
    }

    @PostConstruct
    public void construct() {
        loadFuelCostList();
        loadCars();
        this.sort = Sort.DESC;
        this.idOfCarFilter = -1;
        initializeFuelCostListFiltered();
        filterAndSortList();
    }

    public void sortList(){
        if (sort.equals(Sort.DESC)) {
            Collections.sort(this.fuelCostsListFiltered, Comparator.comparing(FuelCosts::getDate));
        } else {
            Collections.sort(this.fuelCostsListFiltered, Comparator.comparing(FuelCosts::getDate).reversed());
        }
    }

    public void filterList(){
        if (idOfCarFilter<0) {
            initializeFuelCostListFiltered();
        } else {
            fuelCostsListFiltered = fuelCostsList.stream()
                    .filter(record -> record.getIdCar() == idOfCarFilter)
                    .collect(Collectors.toList());
        }
    }

    public void filterAndSortList(){
        filterList();
        sortList();
    }

    public String getGetCarAsString(Integer idOfCar) {
        Car car = carList.stream()
                        .filter(c -> c.getCarId() == idOfCar)
                        .findFirst()
                        .get();
        return car.getBrand() + " " + car.getModel() + " [" + car.getRegPlate() + "]";
    }

    public void showAllCars() {
        this.idOfCarFilter = -1;
    }

    public void showOnlyOneCar(int id) {
        this.idOfCarFilter = id;
    }
}