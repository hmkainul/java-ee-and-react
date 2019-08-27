package com.example.login.boundary;

import java.util.Optional;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("login")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    Login login;

    @POST
    public Response login(JsonObject o) {
        String username = o.getString("username");
        String password = o.getString("password");
        Optional<String> jwt = login.login(username, password);
        if (!jwt.isPresent()) {
            return Response
                .status(Response.Status.UNAUTHORIZED)
                .build();
        }
        JsonObject result = Json.createObjectBuilder()
            .add("token", jwt.get())
            .build();
        return Response.ok(result).build();
    }

}
