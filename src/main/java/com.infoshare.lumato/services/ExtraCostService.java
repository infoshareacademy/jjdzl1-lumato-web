package com.infoshare.lumato.services;

import com.infoshare.lumato.beans.ExtraCostsInputBean;
import com.infoshare.lumato.logic.dao.ExtraCostDao;
import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@ViewScoped
public class ExtraCostService extends PaginationService implements Serializable {

    @Inject
    ExtraCostDao extraCostDao;

    @Inject
    ExtraCostsInputBean extraCostsInputBean;

    public void addExtraCost(ExtraCosts extraCost, Car car) {
        extraCostDao.addExtraCostByCarId(extraCost, car);
    }

    public List<ExtraCosts> getAllExtraCostsByUser() {
        return extraCostDao.getAllExtraCostsByUser();
    }

    private List<ExtraCosts> getExtraCostListByCarId() {
        List<ExtraCosts> extraCostsList = new ArrayList<>();
        for (ExtraCosts extraCosts : extraCostsInputBean.getCompleteExtraCostList()) {
            if (extraCosts.getCarId() == extraCostsInputBean.getCar().getCarId()) {
                extraCostsList.add(extraCosts);
            }
        }
        return extraCostsList;
    }

    public void deleteExtraCost(ExtraCosts extraCosts) {
        extraCostDao.deleteExtraCost(extraCosts);
    }

    @Override
    public void firstPage() {
        super.firstPage();
    }

    @Override
    public void previousPage() {
        super.previousPage();
    }

    @Override
    public void nextPage() {
        super.nextPage();
    }

    @Override
    public void lastPage() {
        super.lastPage();
    }

    @Override
    public int getNumberOfPages() {
        return extraCostDao.getNumberOfPages(itemsOnPage);
    }

    @Override
    public List getCurrentItemsList() {
        return extraCostDao.getItemsPerPage(page, itemsOnPage);
    }

    @Override
    public void setItemsOnPage(int itemsOnPage) {
        super.setItemsOnPage(itemsOnPage);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
