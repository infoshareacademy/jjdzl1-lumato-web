package com.infoshare.lumato.logic.model.chart;

import java.util.Objects;

public class MonthCost {

    private Integer year;
    private Integer month;
    private Double monthFuelCosts;
    private Double monthExtraCosts;
    private Double monthTotalCosts;

    public MonthCost() {
    }

    public MonthCost(Integer year, Integer month, Double monthFuelCosts, Double monthExtraCosts, Double monthTotalCosts) {
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

    public Double getMonthFuelCosts() {
        return monthFuelCosts;
    }

    public void setMonthFuelCosts(Double monthFuelCosts) {
        this.monthFuelCosts = monthFuelCosts;
    }

    public Double getMonthExtraCosts() {
        return monthExtraCosts;
    }

    public void setMonthExtraCosts(Double monthExtraCosts) {
        this.monthExtraCosts = monthExtraCosts;
    }

    public Double getMonthTotalCosts() {
        return monthTotalCosts;
    }

    public void setMonthTotalCosts(Double monthTotalCosts) {
        this.monthTotalCosts = monthTotalCosts;
    }

    @Override
    public String toString() {
        return "MonthCost{" +
                "year=" + year +
                ", month=" + month +
                ", monthFuelCosts=" + monthFuelCosts +
                ", monthExtraCosts=" + monthExtraCosts +
                ", monthTotalCosts=" + monthTotalCosts +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthCost monthCost = (MonthCost) o;
        return Objects.equals(year, monthCost.year) &&
                Objects.equals(month, monthCost.month) &&
                Objects.equals(monthFuelCosts, monthCost.monthFuelCosts) &&
                Objects.equals(monthExtraCosts, monthCost.monthExtraCosts) &&
                Objects.equals(monthTotalCosts, monthCost.monthTotalCosts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, monthFuelCosts, monthExtraCosts, monthTotalCosts);
    }
}
