package com.infoshare.lumato.beans;

import com.infoshare.lumato.models.Car;
import com.infoshare.lumato.models.ExtraCosts;
import com.infoshare.lumato.services.ExtraCostsTableService;
import com.infoshare.lumato.utils.SortOrder;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ViewScoped
@Named("extraCostsTableBean")
public class ExtraCostsTableBean implements Serializable {

    private static final long serialVersionUID = 1709172815224096384L;

    @Inject
    ExtraCostsTableService extraCostsTableService;

    private List<ExtraCosts> extraCostsListFiltered;

    private SortOrder sortOrder;

    private Optional<Integer> idOfCarFilter;

    @PostConstruct
    public void construct() {
        this.sortOrder = SortOrder.DESC;
        this.idOfCarFilter = Optional.empty();
        initializeExtraCostsListFiltered();
        filterAndSortList();
    }

    public List<Car> getCarList(){
        return extraCostsTableService.getCarList();
    }

    private void initializeExtraCostsListFiltered(){
        extraCostsListFiltered = extraCostsTableService.getExtraCostsList().stream()
                .sorted(Comparator.comparing(ExtraCosts::getDate))
                .collect(Collectors.toList());
    }

    public List<ExtraCosts> getExtraCostsListFiltered() {
        filterAndSortList();
        return extraCostsListFiltered;
    }

    public void filterAndSortList(){
        filterListByCar();
        extraCostsTableService.sortListByOrder(extraCostsListFiltered, sortOrder);
    }

    public void filterListByCar(){
        if (!idOfCarFilter.isPresent()) {
            initializeExtraCostsListFiltered();
        } else {
            extraCostsListFiltered = extraCostsTableService.getExtraCostsList().stream()
                    .filter(record -> record.getCarId() == idOfCarFilter.get())
                    .collect(Collectors.toList());
        }
    }

    public String getCarAsString(Integer idOfCar){
        return extraCostsTableService.getCarAsString(idOfCar);
    }

    public void setSortAscending(){
        this.sortOrder = SortOrder.ASC;
    }

    public void setSortDescending(){
        this.sortOrder = SortOrder.DESC;
    }

    public void setAllCarsFilter() {
        this.idOfCarFilter = Optional.empty();
    }

    public void setSingleCarFilter(int id) {
        this.idOfCarFilter = Optional.of(id);
    }
}