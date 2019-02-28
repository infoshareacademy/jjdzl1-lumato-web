package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.FuelCostsDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class FuelsCostsService {

    @Inject
    FuelCostsDAO fuelCostsDAO;



}
