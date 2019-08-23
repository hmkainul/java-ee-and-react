package com.example;

import java.io.IOException;
import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        if (!isAuthenticated(context)) {
            abort(context);
        }
    }

    private boolean isAuthenticated(ContainerRequestContext context) {
        return false;
    }

    private void abort(ContainerRequestContext context) {
        Response response = Response
            .status(Response.Status.UNAUTHORIZED)
            .build();
        context.abortWith(response);
    }

}
