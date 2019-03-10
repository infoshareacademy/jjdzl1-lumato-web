package com.infoshare.lumato.utils;

public enum FuelType {

    PETROL("Petrol"), DIESEL("Diesel"), PLPG("P+LPG");

    private String name;

    FuelType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
