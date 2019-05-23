package com.infoshare.lumato.filters;

import com.infoshare.lumato.services.TokenService;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@Provider
public class AuthenticationFilter implements ContainerRequestFilter {

    @Inject
    TokenService tokenService;

    @Override
    public void filter(ContainerRequestContext requestContext) {
        MultivaluedMap<String, String> headers = requestContext.getHeaders();
        if (headers.get("UID") != null && headers.get("FT") != null) {
            String id = headers.get("UID").get(0);
            String token = headers.get("FT").get(0);
            System.out.println("ID: " + id + ", TOKEN: " + token);

            boolean tokenIsFine = tokenService.isTokenValid(Integer.valueOf(id), token);

            if (!tokenIsFine) {
                returnForbiddenResponse(requestContext);
                return;
            }

        } else {
            returnForbiddenResponse(requestContext);
            return;
        }
    }

    public void returnForbiddenResponse(ContainerRequestContext requestContext) {
        Response.Status resStatus = Response.Status.FORBIDDEN;
        requestContext.abortWith(Response
                .status(resStatus)
                .type(MediaType.APPLICATION_JSON)
                .entity(Response.Status.FORBIDDEN)
                .build());
    }
}