package com.test.lab03.services;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public interface APIService {
    @POST
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path(value = "/{versionNo}/{serviceFunction}")
    public Response executeService(String apiRequestStr);

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Path(value = "/{versionNo}/{serviceFunction}")
    public Response getServiceResults();
}