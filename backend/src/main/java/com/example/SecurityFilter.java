package com.example;

import java.io.IOException;
import java.util.Optional;
import javax.annotation.Priority;
import javax.inject.Inject;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.*;
import javax.ws.rs.core.*;
import javax.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class SecurityFilter implements ContainerRequestFilter {

    private static final String BEARER = "Bearer ";

    @Inject
    Tokens tokens;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        if (isLoginService(context)) {
            return;
        }
        if (!isAuthenticated(context)) {
            abort(context);
        }
    }

    private boolean isLoginService(ContainerRequestContext context) {
        String path = context.getUriInfo().getPath();
        return path.startsWith("login");
    }

    private boolean isAuthenticated(ContainerRequestContext context) {
        String header = context.getHeaderString(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(BEARER)) {
            String token = header.substring(BEARER.length());
            Optional<String> username = tokens.getUsernameFromToken(token);
            if (username.isPresent()) {
                return true;
            }
        }
        return false;
    }

    private void abort(ContainerRequestContext context) {
        Response response = Response
            .status(Response.Status.UNAUTHORIZED)
            .build();
        context.abortWith(response);
    }

}
