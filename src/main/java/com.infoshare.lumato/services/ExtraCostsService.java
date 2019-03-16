package com.infoshare.lumato.services;

import com.infoshare.lumato.beans.ExtraCostsBean;
import com.infoshare.lumato.dao.CarDAO;
import com.infoshare.lumato.dao.ExtraCostsDAO;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
import com.infoshare.lumato.models.FuelCosts;
import com.infoshare.lumato.utils.FuelCostComparatorByDate;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@RequestScoped
public class ExtraCostsService {

    @Inject
    ExtraCostsDAO extraCostsDAO;

    @Inject
    CarDAO carDAO;

    @Inject
    ExtraCostsBean extraCostsBean;

    public void addExtraCosts(ExtraCosts extraCosts, Car car) {
        extraCostsDAO.addExtraCostsByCarId(ExtraCosts, car);
    }

    public List<Car> getAllCarsByUser() {
        return carDAO.getAllCarsByUser();
    }

    public List<FuelCosts> getAllExtraCostsByUser() {
        return extraCostsDAO.getAllExtraCostsByUser();
    }

    public boolean isExtraCostsEmpty(ExtraCosts extraCosts) {
        return (extraCosts.getCosts() > 0);
    }

    private List<ExtraCosts> getExtraCostsListByCarId() {
        List<ExtraCosts> extraCostListByCarId = new ArrayList<>();
        for (ExtraCosts extraCosts : ExtraCostsBean.getCompleteExtraCostsList()) {
            if (extraCosts.getIdCar() == ExtraCostsBean.getCar().getCarId()) {
                extraCostsListByCarId.add(extraCosts);
            }
        }
        return getExtraCostsListByCarId;
    }
    public void deleteExtraCost(ExtraCosts extraCosts) {ExtraCostsDAO.deleteExtraCosts(extraCosts);
    }

}
