package com.example.compounds.boundary;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import com.example.compounds.entity.Compound;


@Path("compounds")
@Stateless
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CompoundsResource {

    @Inject
    Compounds service;

    @GET
    @Path("{id}")
    public Response get(@PathParam("id") int id) {
        Compound result = service.get(id);
        return Response
            .ok(result)
            .build();
    }
        
    @GET
    public Response getAll() {
        List<Compound> result = service.getAll();
        return Response
            .ok(result)
            .build();
    }

    @POST
    public Response save(Compound compound) {
        Compound result = service.save(compound);
        return Response
            .ok(result)
            .build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") int id) {
        service.delete(id);
        return Response
            .noContent()
            .build();
    }

}
