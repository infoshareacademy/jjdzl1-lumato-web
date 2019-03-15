package com.infoshare.lumato.services;

import com.infoshare.lumato.beans.ExtraCostsInputBean;
import com.infoshare.lumato.dao.ExtraCostDao;
import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;


@RequestScoped
public class ExtraCostService {

    @Inject
    ExtraCostDao extraCostDao;

    @Inject
    ExtraCostsInputBean extraCostsInputBean;

    public void addExtraCost(ExtraCosts extraCost, Car car) {
        extraCostDao.addExtraCostByCarId(extraCost,car);
    }

    public List<ExtraCosts> getAllExtraCostsByUser() {
        return extraCostDao.getAllExtraCostsByUser();
    }

    private List<ExtraCosts> getExtraCostListByCarId(){
        List<ExtraCosts> extraCostsList = new ArrayList<>();
        for (ExtraCosts extraCosts : extraCostsInputBean.getCompleteExtraCostList()) {
            if(extraCosts.getCarId() == extraCostsInputBean.getCar().getCarId()) {
                extraCostsList.add(extraCosts);
            }
        }
        return extraCostsList;
    }
}
