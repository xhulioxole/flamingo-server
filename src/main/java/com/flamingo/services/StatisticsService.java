package com.flamingo.services;

import com.flamingo.Constants;
import com.flamingo.controllers.StatisticsController;
import com.flamingo.utils.JWT;
import com.flamingo.utils.ResponseUtils;
import com.flamingo.utils.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;

@Path("/statistics")
public class StatisticsService {

    @GET
    @Path("/map")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMapStats(
            @QueryParam("startDate") long startDate,
            @QueryParam("endDate")  long endDate
    ) {
        JSONObject mapStats = StatisticsController.getMapStats(new Date(startDate), new Date(endDate));
        return ResponseUtils.ok(mapStats);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatistics(
            @QueryParam("locationId") @DefaultValue("") String locationId,
            @QueryParam("startDate") long startDate,
            @QueryParam("endDate")  long endDate
    ) {
        JSONObject statistics = StatisticsController.getStatistics(locationId, new Date(startDate), new Date(endDate));
        return ResponseUtils.ok(statistics);
    }

    @GET
    @Path("/locations")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatsBasedOnLocations(
            @QueryParam("startDate") Date startDate,
            @QueryParam("endDate") Date endDate
    ) {
        JSONArray locationStatistics = StatisticsController.getLocationStatistics(startDate, endDate);
        return ResponseUtils.ok(locationStatistics);
    }

    @GET
    @Path("/weekly")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getWeeklyStats(
            @QueryParam("locationId") @DefaultValue("") String locationId,
            @QueryParam("startDate") Date startDate,
            @QueryParam("endDate") Date endDate
    ) {
        JSONArray locationStatistics = StatisticsController.getWeeklyStatistics(locationId, startDate, endDate);
        return ResponseUtils.ok(locationStatistics);
    }

    @GET
    @Path("/daily")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDailyStats(
            @QueryParam("locationId") @DefaultValue("") String locationId,
            @QueryParam("startDate") Date startDate,
            @QueryParam("endDate") Date endDate
    ) {
        JSONArray locationStatistics = StatisticsController.getDailyStatistics(locationId, startDate, endDate);
        return ResponseUtils.ok(locationStatistics);
    }

    @GET
    @Path("/location/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatsByLocation(
        @PathParam("id") String locationId
    ) {
        JSONArray trafficData = StatisticsController.getDataByLocationId(locationId);
        return ResponseUtils.ok(trafficData);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStatistic(
            @Context HttpHeaders httpHeaders,
            @FormParam("locationId") String locationId,
            @FormParam("vehicles") int vehiclesNumber
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                JSONObject trafficData = StatisticsController.createStatistic(locationId, vehiclesNumber);
                return ResponseUtils.ok(trafficData);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }
}
