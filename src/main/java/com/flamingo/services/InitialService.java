package com.flamingo.services;

import com.flamingo.Constants;
import com.flamingo.controllers.LocationController;
import com.flamingo.controllers.SupportController;
import com.flamingo.controllers.UserController;
import com.flamingo.utils.JWT;
import com.flamingo.utils.ResponseUtils;
import com.flamingo.utils.StringUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Xhulio on 2/15/2016.
 */
@Path("/initial")
public class InitialService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInitialData(
            @Context HttpHeaders httpHeaders
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                JSONArray locations = LocationController.getLocations();
                JSONArray messages = SupportController.getMessages();
                JSONObject data = new JSONObject();
                data.put(Constants.Keys.LOCATIONS, locations);
                data.put(Constants.Keys.MESSAGES, messages);
                return ResponseUtils.ok(data);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }

    }
}
