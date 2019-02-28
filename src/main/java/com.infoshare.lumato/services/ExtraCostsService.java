package com.infoshare.lumato.services;

import com.infoshare.lumato.dao.ExtraCostsDAO;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class ExtraCostsService {

    @Inject
    ExtraCostsDAO extraCostsDAO;

}
