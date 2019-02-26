package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.utils.FuelType;
import com.infoshare.lumato.utils.HttpUtils;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@RequestScoped
@Named("editCar")
public class CarEditBean {

    private String newBrand;
    private String newModel;
    private String newFuelType;
    private int newProductionYear;
    private int newRegPlate;

    private FuelType[] fuelTypes;

    public String getNewBrand() {
        return newBrand;
    }

    public void setNewBrand(String newBrand) {
        this.newBrand = newBrand;
    }

    public String getNewModel() {
        return newModel;
    }

    public void setNewModel(String newModel) {
        this.newModel = newModel;
    }

    public String getNewFuelType() {
        return newFuelType;
    }

    public void setNewFuelType(String newFuelType) {
        this.newFuelType = newFuelType;
    }

    public int getNewProductionYear() {
        return newProductionYear;
    }

    public void setNewProductionYear(int newProductionYear) {
        this.newProductionYear = newProductionYear;
    }

    public int getNewRegPlate() {
        return newRegPlate;
    }

    public void setNewRegPlate(int newRegPlate) {
        this.newRegPlate = newRegPlate;
    }

    @Inject
    CarsService carsService;

    @PostConstruct
    public void construct() {
        fuelTypes = FuelType.values();
    }

    public FuelType[] getFuelTypes() {
        return fuelTypes;
    }

    private Car car = new Car();

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void updateCar(Car theCar) {
        carsService.editCar(theCar);
        HttpUtils.redirect("/app/cars-input.xhtml");
    }

}
