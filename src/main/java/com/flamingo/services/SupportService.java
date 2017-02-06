package com.flamingo.services;

import com.flamingo.Constants;
import com.flamingo.controllers.SupportController;
import com.flamingo.utils.JWT;
import com.flamingo.utils.ResponseUtils;
import com.flamingo.utils.StringUtils;
import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Xhulio on 2/2/2016.
 */

@Path("/support")
public class SupportService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessage(
            @Context HttpHeaders httpHeaders
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                JSONArray messages = SupportController.getMessages();
                return ResponseUtils.ok(messages);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }

    @PUT
    public Response createSupportMessage(
            @FormParam("name") String name,
            @FormParam("from") String from,
            @FormParam("message") String message
    ) {
        boolean isCreated = SupportController.createSupportMessage(name, from, message);
        if (!isCreated) {
            return ResponseUtils.error(Constants.Messages.MAIL_ERROR);
        }
        return Response.ok().build();
    }

    @POST
    @Path("{id}")
    public Response updateMessage(
            @Context HttpHeaders httpHeaders,
            @PathParam("id") String id,
            @FormParam("name") String name,
            @FormParam("from") String from,
            @FormParam("message") String message
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                Document messageDocument = SupportController.updateMessage(id, name, from, message);
                return ResponseUtils.ok(messageDocument);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteMessage(
            @Context HttpHeaders httpHeaders,
            @PathParam("id") String id
    ) {
        try {
            String token = StringUtils.getAuthorizationHeaderValue(String.valueOf(httpHeaders.getRequestHeader("Authorization")));
            if (!JWT.isValid(token)) {
                return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
            } else {
                JSONObject result = SupportController.deleteMessage(id);
                return ResponseUtils.ok(result);
            }
        } catch (Exception e) {
            return ResponseUtils.unauthorized(Constants.Messages.INVALID_TOKEN);
        }
    }
}
