package com.infoshare.lumato.controllers;

import com.infoshare.lumato.logic.dao.chart.MonthlyCostsDAO;
import com.infoshare.lumato.logic.model.chart.MonthCost;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/charts")
public class MonthlyCostsController {

    @Inject
    MonthlyCostsDAO monthlyCostsDAO;

    @GET
    @Path("/test")
    @Produces({MediaType.TEXT_PLAIN})
    public Response getStuff(){
        return Response.ok("DAMN SON").build();
    }

    @GET
    @Path("/costs-per-month")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getMonthlyCosts(@QueryParam("user_id") int userId,
                                    @QueryParam("limit") int limit){
        if (limit==0) limit=10;
        List<MonthCost> monthCosts = monthlyCostsDAO.getMonthlyCostsFromTenLastMonths(userId, limit);
        if (monthCosts == null || monthCosts.size() == 0) {
            return Response.noContent().tag("User with user_id="+userId+" does not exist!").build();
        } else {
            return Response.ok(monthCosts).build();
        }
    }

}
