package com.example.elements.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.example.elements.entity.Element;

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
        Element result = service.get(number);
        return Response
            .ok(result)
            .build();
    }
        
    @GET
    public Response getAll() {
        List<Element> result = service.getAll();
        return Response
            .ok(result)
            .build();
    }

    @POST
    public Response save(Element element) {
        Element result = service.save(element);
        return Response
            .ok(result)
            .build();
    }

    @DELETE
    @Path("{number}")
    public Response delete(@PathParam("number") int number) {
        service.delete(number);
        return Response
            .noContent()
            .build();
    }

}
