package com.infoshare.lumato.logic.dao.chart;

import com.infoshare.lumato.logic.dao.CommonDAO;
import com.infoshare.lumato.logic.model.chart.MonthCost;
import org.hibernate.Session;
import org.hibernate.jdbc.ReturningWork;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
@Named
public class MonthlyCostsDAO extends CommonDAO {

    public List<MonthCost> getMonthlyCostsFromTenLastMonths(int userId, int limit) {
        if (userId==0) return new ArrayList<MonthCost>();
        if (limit>60) limit=60;
        Session currentSession = getSession();
        int finalLimit = limit;

        List<MonthCost> result = currentSession.doReturningWork(new ReturningWork<List<MonthCost>>() {
            @Override
            public List<MonthCost> execute(Connection connection) throws SQLException {
                String sqlQuery = "SELECT MT.theyear AS theyear, MT.themonth AS themonth, SUM(MT.monthFuelCost) AS monthFuelCost, SUM(MT.monthExtraCost) AS monthExtraCost, SUM(MT.monthTotalCost) AS monthTotalCost " +
                        "FROM ( " +
                            "SELECT " +
                                "ROUND(fuelcost.price_per_liter*fuelcost.amount_of_fuel,2) as monthFuelCost, " +
                                "0 as monthExtraCost, " +
                                "EXTRACT(YEAR from fuelcost.date) as theyear, " +
                                "EXTRACT(MONTH from fuelcost.date) as themonth, " +
                                "fuelcost.car_id as car_id, ROUND(fuelcost.price_per_liter*fuelcost.amount_of_fuel,2) as monthTotalCost " +
                            "FROM fuelcost " +
                            "WHERE fuelcost.user_id=? " +
                            "AND fuelcost.date > DATE(CURRENT_DATE - INTERVAL ? MONTH) " +
                        "UNION " +
                            "SELECT " +
                                "0 as monthFuelCost, " +
                                "extracost.cost as monthExtraCost, " +
                                "EXTRACT(YEAR from extracost.cost_date) as theyear, " +
                                "EXTRACT(MONTH from extracost.cost_date) as themonth, " +
                                "extracost.car_id as car_id, " +
                                "extracost.cost as monthTotalCost " +
                            "FROM extracost " +
                            "WHERE extracost.user_id=? " +
                            "AND extracost.cost_date > DATE(CURRENT_DATE - INTERVAL ? MONTH) " +
                        ") AS MT " +
                            "GROUP BY MT.theyear, MT.themonth " +
                            "ORDER BY MT.theyear DESC, MT.themonth DESC";
                PreparedStatement statement = connection.prepareStatement(sqlQuery);
                statement.setInt(1, userId);
                statement.setInt(2, finalLimit -1);
                statement.setInt(3, userId);
                statement.setInt(4, finalLimit -1);
                ResultSet rs = statement.executeQuery();
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
                if (monthlyCostsList.size()==0) return monthlyCostsList;

                List<LocalDate> dates = monthlyCostsList.stream()
                        .map(cost -> LocalDate.of(cost.getYear(), cost.getMonth(), 1))
                        .collect(Collectors.toList());
                LocalDate date = LocalDate.of(LocalDate.now().getYear(), LocalDate.now().getMonthValue(), 1);
                for (int i = 0; i< finalLimit; i++) {
                    if (!dates.contains(date)) {
                        MonthCost zeroCost = new MonthCost(date.getYear(), date.getMonthValue(), 0.0, 0.0,0.0);
                        monthlyCostsList.add(zeroCost);
                    }
                    date = date.minusMonths(1L);
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
