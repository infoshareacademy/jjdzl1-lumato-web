package com.infoshare.lumato.services;

import com.infoshare.lumato.logic.dao.ExtraCostDao;
import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.logic.model.ExtraCosts;
import com.infoshare.lumato.logic.model.User;
import com.infoshare.lumato.utils.HttpUtils;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.List;


@ViewScoped
public class ExtraCostService extends PaginationService implements Serializable, Service {

    @Inject
    ExtraCostDao extraCostDao;

    User currentUser = HttpUtils.getCurrentUserFromSession();

    public void addExtraCost(ExtraCosts extraCost, Car car) {
        extraCostDao.addExtraCostByCarId(extraCost, car, currentUser.getUserId());
    }

    @Override
    public void deleteObject(Object extraCosts) {
        extraCostDao.deleteObject(extraCosts);
    }

    @Override
    public int getNumberOfPages() {
        return extraCostDao.getNumberOfPages(ExtraCosts.class, itemsOnPage, currentUser.getUserId());
    }

    @Override
    public List getCurrentItemsList() {
        return extraCostDao.getItemsPerPage(page, itemsOnPage, ExtraCosts.class, currentUser.getUserId());
    }

    @Override
    public void setItemsOnPage(int itemsOnPage) {
        super.setItemsOnPage(itemsOnPage);
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
    public void addObject(Object obj) {
    }

    @Override
    public void updateObject(Object obj) {
    }

    @Override
    public Object getObjectById(int id) {
        return null;
    }

    @Override
    public int getPage() {
        return page;
    }

    @Override
    public void setPage(int page) {
        this.page = page;
    }
}
