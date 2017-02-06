package com.flamingo.services;

import com.flamingo.Constants;
import com.flamingo.controllers.LocationController;
import com.flamingo.controllers.UserController;
import com.flamingo.utils.JWT;
import com.flamingo.utils.ResponseUtils;
import com.flamingo.utils.StringUtils;
import com.flamingo.utils.Utils;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Xhulio on 2/8/2016.
 */

@Path("/locations")
public class LocationService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocations() {
        JSONArray locations = LocationController.getLocations();
        return ResponseUtils.ok(locations);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLocationById(
            @PathParam("id") String locationId
    ) {
        Document location = LocationController.getLocationById(locationId);
        return ResponseUtils.ok(location);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response createLocation(
            @Context HttpHeaders httpHeaders,
            @FormParam("name") String name,
            @FormParam("lat") String lat,
            @FormParam("lng") String lng
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                Document location = LocationController.createLocation(name, lat, lng);
                return ResponseUtils.ok(location);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }

    @POST
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateLocation(
            @Context HttpHeaders httpHeaders,
            @PathParam("id") String id,
            @FormParam("name") String name,
            @FormParam("lat") String lat,
            @FormParam("lng") String lng
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                Document location = LocationController.updateLocation(id, name, lat, lng);
                return ResponseUtils.ok(location);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteLocation(
            @Context HttpHeaders httpHeaders,
            @PathParam("id") String id
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                JSONObject result= LocationController.deleteLocation(id);
                return ResponseUtils.ok(result);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }
}
