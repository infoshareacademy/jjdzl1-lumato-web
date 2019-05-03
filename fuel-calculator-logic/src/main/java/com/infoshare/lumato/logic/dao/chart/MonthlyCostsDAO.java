package com.infoshare.lumato.logic.dao.chart;

import com.infoshare.lumato.logic.dao.CommonDAO;
import com.infoshare.lumato.logic.model.chart.MonthCost;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class MonthlyCostsDAO extends CommonDAO {

    public List<MonthCost> getMonthlyCostsFromTenLastMonths(int userId) {
        Session currentSession = getSession();

        List<MonthCost> result = currentSession.doReturningWork(new ReturningWork<List<MonthCost>>() {
            @Override
            public List<MonthCost> execute(Connection connection) throws SQLException {
                String sqlQuery = "SELECT MT.theyear AS theyear, MT.themonth AS themonth, SUM(MT.monthFuelCost) AS monthFuelCost, SUM(MT.monthExtraCost) AS monthExtraCost, SUM(MT.monthTotalCost) AS monthTotalCost FROM ( SELECT ROUND(fuelcost.price_per_liter*fuelcost.amount_of_fuel,2) as monthFuelCost, 0 as monthExtraCost, EXTRACT(YEAR from fuelcost.date) as theyear, EXTRACT(MONTH from fuelcost.date) as themonth, fuelcost.car_id as car_id, ROUND(fuelcost.price_per_liter*fuelcost.amount_of_fuel,2) as monthTotalCost FROM fuelcost WHERE fuelcost.user_id=7 UNION SELECT 0 as monthFuelCost, extracost.cost as monthExtraCost, EXTRACT(YEAR from extracost.cost_date) as theyear, EXTRACT(MONTH from extracost.cost_date) as themonth, extracost.car_id as car_id, extracost.cost as monthTotalCost FROM extracost WHERE extracost.user_id=7 ) AS MT GROUP BY MT.theyear, MT.themonth ORDER BY MT.theyear DESC, MT.themonth DESC LIMIT 10";
//                Statement statement = connection.prepareStatement(sqlQuery);
                Statement statement = connection.createStatement();
//                statement.setInt(1, userId);
//                statement.setInt(2, userId);
                ResultSet rs = statement.executeQuery(sqlQuery);
                List<MonthCost> monthlyCostsList = new ArrayList<>();
                while(rs.next()) {
                    MonthCost monthCost = new MonthCost();
                    monthCost.setYear(rs.getInt("theyear"));
                    monthCost.setMonth(rs.getInt("themonth"));
                    monthCost.setMonthFuelCosts(rs.getDouble("monthFuelCost"));
                    monthCost.setMonthExtraCosts(rs.getDouble("monthExtraCost"));
                    monthCost.setMonthTotalCosts(rs.getDouble("monthTotalCost"));
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
