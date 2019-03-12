package com.infoshare.lumato.utils;

import com.infoshare.lumato.models.FuelCosts;

import java.util.Comparator;

public class FuelCostComparatorByMileage implements Comparator<FuelCosts> {
    @Override
    public int compare(FuelCosts o1, FuelCosts o2) {
        int val1 = o1.getCurrentMileage();
        int val2 = o2.getCurrentMileage();
        if (val1 > val2) return 1;
        else if (val1 < val2) return -1;
        return 0;
    }
}
