package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.ExtraCostsTableDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
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
public class ExtraCostsTableService implements Serializable {

    private static final long serialVersionUID = -8569464224056311242L;

    @Inject
    ExtraCostsTableDAO repository;

    // TODO: 15.03.2019 zamienic na car.getCarList
    public List<Car> getCarList(){
        return this.repository.getCarList();
    }

    public List<ExtraCosts> getExtraCostsList(){
        return this.repository.getExtraCostsList();
    }

    public void sortListByOrder(List<ExtraCosts> extraCostsListFiltered, SortOrder sortOrder){
        if (sortOrder.equals(SortOrder.ASC)) {
            Collections.sort(extraCostsListFiltered, Comparator.comparing(ExtraCosts::getDate));
        } else {
            Collections.sort(extraCostsListFiltered, Comparator.comparing(ExtraCosts::getDate).reversed());
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
