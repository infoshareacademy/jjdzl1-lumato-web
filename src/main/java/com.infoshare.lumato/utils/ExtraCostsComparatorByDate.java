package com.infoshare.lumato.utils;

import com.infoshare.lumato.models.ExtraCosts;

import java.util.Comparator;

public class ExtraCostsComparatorByDate implements Comparator <ExtraCosts> {
    @Override
    public int compare(ExtraCosts o1, ExtraCosts o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
}
