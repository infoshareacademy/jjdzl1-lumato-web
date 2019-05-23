package com.infoshare.lumato.utils;


import com.infoshare.lumato.logic.model.FuelCosts;

import java.util.Comparator;

public class FuelCostComparatorByDate implements Comparator<FuelCosts> {
    @Override
    public int compare(FuelCosts o1, FuelCosts o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
