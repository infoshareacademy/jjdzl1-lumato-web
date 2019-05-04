package com.infoshare.lumato.services;

import java.util.List;

public interface Service {

    List getCurrentItemsList();

    void addObject(Object obj);

    void deleteObject(Object obj);

    void updateObject(Object obj);

    int getNumberOfPages();

    void previousPage();

    void nextPage();

    void firstPage();

    void lastPage();

    int getPage();

    void setPage(int page);

    void setItemsOnPage(int itemsOnPage);

    Object getObjectById(int id);



}
