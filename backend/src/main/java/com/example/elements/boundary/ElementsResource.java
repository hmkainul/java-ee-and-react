package com.example.elements.boundary;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("elements")
public class ElementsResource {

    @GET
    public Response get() {
        return Response.ok("Hello, World!").build();
    }

}
