package com.infoshare.lumato.beans;

import com.infoshare.lumato.logic.model.Car;
import com.infoshare.lumato.services.MessageService;
import com.infoshare.lumato.services.Service;
import lombok.Data;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Data
public abstract class Bean {

    @Inject
    MessageService messageService;

    int page;

    int itemsOnPage;

    List<Integer> pageList;

    int[] itemsShowOnPage = {4, 8, 12};

    Car car = new Car();

    Service service;

    public void previousPage() {
        service.previousPage();
    }

    public void nextPage() {
        service.nextPage();
    }

    public void firstPage() {
        service.firstPage();
    }

    public void lastPage() {
        service.lastPage();
    }

    public void getCurrentPage() {
        page = service.getPage();
    }

    public void goToSelectedPage() {
        service.setPage(page);
        service.getCurrentItemsList();
    }

    public void setNumberOfItemsOnPage() {
        service.setItemsOnPage(itemsOnPage);
        service.setPage(page = 1);
        service.getCurrentItemsList();
    }

    public List<Integer> getListOfPages() {
        pageList = new ArrayList<>();
        IntStream.rangeClosed(1, service.getNumberOfPages()).
                forEachOrdered(i -> pageList.add(i));
        return pageList;
    }
}
