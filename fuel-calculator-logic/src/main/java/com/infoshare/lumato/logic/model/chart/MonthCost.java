package com.infoshare.lumato.logic.model.chart;

public class MonthCost {

    private Integer year;
    private Integer month;
    private Integer monthFuelCosts;
    private Integer monthExtraCosts;
    private Integer monthTotalCosts;

    public MonthCost() {
    }

    public MonthCost(Integer year, Integer month, Integer monthFuelCosts, Integer monthExtraCosts, Integer monthTotalCosts) {
        this.year = year;
        this.month = month;
        this.monthFuelCosts = monthFuelCosts;
        this.monthExtraCosts = monthExtraCosts;
        this.monthTotalCosts = monthTotalCosts;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getMonthFuelCosts() {
        return monthFuelCosts;
    }

    public void setMonthFuelCosts(Integer monthFuelCosts) {
        this.monthFuelCosts = monthFuelCosts;
    }

    public Integer getMonthExtraCosts() {
        return monthExtraCosts;
    }

    public void setMonthExtraCosts(Integer monthExtraCosts) {
        this.monthExtraCosts = monthExtraCosts;
    }

    public Integer getMonthTotalCosts() {
        return monthTotalCosts;
    }

    public void setMonthTotalCosts(Integer monthTotalCosts) {
        this.monthTotalCosts = monthTotalCosts;
    }
}
