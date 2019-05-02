package com.infoshare.lumato.services;

import java.util.List;

abstract class PaginationService {

    int page = 1;

    int itemsOnPage = 4;


    void firstPage() {
        page = 1;
    }

    void previousPage() {
        if (page <= 1) page = 1;
        else page--;
    }

    void nextPage() {
        int lastPage = getNumberOfPages();
        if (page == lastPage) page = lastPage - 1;
        page++;
    }

    void lastPage() {
        if (getNumberOfPages() == 0) {
            page = 1;
        } else page = getNumberOfPages();
    }

    abstract int getNumberOfPages();

    abstract List getCurrentItemsList();
}
