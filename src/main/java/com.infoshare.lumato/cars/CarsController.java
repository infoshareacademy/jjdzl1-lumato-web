package com.infoshare.lumato.cars;


import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

@Named
@RequestScoped
public class CarsController {

    private List<CarsBean> cars = new ArrayList<>();


}
