package com.flamingo.services;

import com.flamingo.Constants;
import com.flamingo.controllers.PushController;
import com.flamingo.utils.JWT;
import com.flamingo.utils.ResponseUtils;
import com.flamingo.utils.StringUtils;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Xhulio on 2/15/2016.
 */
@Path("/push")
public class PushService {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendPush(
            @Context HttpHeaders httpHeaders,
            @FormParam("title") String title,
            @FormParam("message") String message
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                PushController.sendPush(title, message);
                return ResponseUtils.ok();
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }
}
