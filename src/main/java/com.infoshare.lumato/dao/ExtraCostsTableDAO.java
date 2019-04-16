package com.infoshare.lumato.dao;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class ExtraCostsTableDAO implements Serializable {

    private static final long serialVersionUID = 6256489859167556819L;

    private List<ExtraCosts> extraCostsList;

    private List<Car> carList;

    public List<ExtraCosts> getExtraCostsList() {
        return extraCostsList;
    }

    public List<Car> getCarList() {
        return carList;
    }

    @Inject
    ExtraCostDao extraCostDao;

    @Inject
    CarDAO carDAO;

    @PostConstruct
    public void construct() {
        this.carList = this.carDAO.getAllCarsByUser();
        this.extraCostsList = this.extraCostDao.getAllExtraCostsByUser();
    }

}
