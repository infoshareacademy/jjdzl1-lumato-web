package com.infoshare.lumato.logic.dao.chart;

import com.infoshare.lumato.logic.dao.CommonDAO;
import com.infoshare.lumato.logic.model.chart.MonthCost;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MonthlyCostsDAO extends CommonDAO {

    public List<MonthCost> getMonthlyCostsFromTenLastMonths() {
        Session currentSession = getSession();

        List<MonthCost> result = currentSession.doReturningWork(new ReturningWork<List<MonthCost>>() {
            @Override
            public List<MonthCost> execute(Connection connection) throws SQLException {
                String sqlQuery = "SELECT MT.theyear, MT.themonth, SUM(MT.monthFuelCost) AS monthFuelCost, SUM(MT.monthExtraCost) AS monthExtraCost, SUM(MT.monthTotalCost) AS monthTotalCost FROM ( SELECT ROUND(fuelcost.price_per_liter*fuelcost.amount_of_fuel,2) as monthFuelCost, 0 as monthExtraCost, EXTRACT(YEAR from fuelcost.date) as theyear, EXTRACT(MONTH from fuelcost.date) as themonth, fuelcost.car_id as car_id, ROUND(fuelcost.price_per_liter*fuelcost.amount_of_fuel,2) as monthTotalCost FROM fuelcost UNION SELECT 0 as monthFuelCost, extracost.cost as monthExtraCost, EXTRACT(YEAR from extracost.cost_date) as theyear, EXTRACT(MONTH from extracost.cost_date) as themonth, extracost.car_id as car_id, extracost.cost as monthTotalCost FROM extracost ) AS MT GROUP BY MT.theyear, MT.themonth ORDER BY MT.theyear DESC, MT.themonth DESC LIMIT 10";
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery(sqlQuery);
                List<MonthCost> monthlyCostsList = new ArrayList<>();
                while(rs.next()) {
                    MonthCost monthCost = new MonthCost();
                    monthCost.setYear(rs.getInt("theyear"));
                    monthCost.setMonth(rs.getInt("themonth"));
                    monthCost.setMonthFuelCosts(rs.getInt("monthFuelCost"));
                    monthCost.setMonthExtraCosts(rs.getInt("monthExtraCost"));
                    monthCost.setMonthTotalCosts(rs.getInt("monthTotalCost"));
                    monthlyCostsList.add(monthCost);
                }
                return monthlyCostsList
                        .stream()
                        .sorted(Comparator.comparing(MonthCost::getYear).thenComparing(MonthCost::getMonth))
                        .collect(Collectors.toList());
            }
        });

        executeAndCloseTransaction(currentSession);
        return result;
    }

}
