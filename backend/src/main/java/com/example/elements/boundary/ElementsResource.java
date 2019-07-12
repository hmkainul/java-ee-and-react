package com.example.elements.boundary;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("elements")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ElementsResource {

    @Inject
    Elements service;

    @GET
    @Path("{number}")
    public Response get(@PathParam("number") int number) {
        return Response.ok(service.get(number)).build();
    }

}
