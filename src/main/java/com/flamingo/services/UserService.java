package com.flamingo.services;

import com.flamingo.Constants;
import com.flamingo.controllers.UserController;
import com.flamingo.utils.JWT;
import com.flamingo.utils.ResponseUtils;
import com.flamingo.utils.StringUtils;
import com.nimbusds.jose.JOSEException;
import com.sun.org.apache.xml.internal.resolver.Catalog;

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

@Path("/user")
public class UserService {

    @POST
    @Path("/login")
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @Context HttpHeaders httpHeaders,
            @FormParam("email") String email,
            @FormParam("password") String password
    ) {
        try {
            return UserController.login(email, password);
        } catch (JOSEException e) {
            return ResponseUtils.error(Constants.Messages.BAD_REQUEST);
        }
    }
}
