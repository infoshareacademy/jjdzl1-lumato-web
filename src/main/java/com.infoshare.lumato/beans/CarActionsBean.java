package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.services.CarsService;
import com.infoshare.lumato.utils.FuelType;
import com.infoshare.lumato.utils.HttpUtils;
import net.bootsfaces.C;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@RequestScoped
@Named("carBean")
public class CarActionsBean implements Serializable {

    @Inject
    private CarsService carsService;

    private Car car = new Car();

    private FuelType[] fuelTypes;

    private List<Car> carList;

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @PostConstruct
    public void construct() {
        fuelTypes = FuelType.values();
        loadCars();
    }

    public FuelType[] getFuelTypes() {
        return fuelTypes;
    }

    public List<Car> getCars() {
        return carList;
    }

    private void loadCars() {
        try {
            carList = carsService.getAllCarsByUser();
        } catch (Exception e) {
            System.out.println("Cannot load users!");
            e.printStackTrace();
        }
    }

    private void addNewCar() {
        carsService.addCar(car);
        redirectToCarPage();
    }

    private void deleteCar() {
        carsService.deleteCar(car);
        redirectToCarPage();
    }

    public void attemptToAddCar() {
        if(carsService.doesCarExist(car)) {
            HttpUtils.redirect("/app/start.xhtml");
        }
        addNewCar();
    }

    public void attemptToDeleteCar(Car theCar) {
        setCar(theCar);
        deleteCar();
    }

    private void redirectToCarPage(){
        HttpUtils.redirect("/app/cars-input.xhtml");
    }
}




























