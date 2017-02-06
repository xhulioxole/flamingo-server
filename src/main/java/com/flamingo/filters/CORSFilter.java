package com.flamingo.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by Xhulio on 2/2/2016.
 */
@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        responseContext.getHeaders().add("Access-Control-Allow-Headers", "Authorization, Accept, Content-Type");
        responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, PUT, POST, OPTIONS, DELETE");
        responseContext.getHeaders().add("X-Frame-Options", "SAMEORIGIN");
        responseContext.getHeaders().add("X-Content-Type-Options", "nosniff");
    }
}

