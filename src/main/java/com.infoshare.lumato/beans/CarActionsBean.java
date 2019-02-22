package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.services.CarsService;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

@RequestScoped
@Named("carActionBean")
public class CarActionsBean {

    private List<Car> carList;


    @Inject
    private CarsService carsService;

    private Car car;

    public List<Car> getCars(){
        loadCars();
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


    public Car getCar(){
        return car;
    }

    public void setCar(Car car){
        this.car = car;
    }

}
