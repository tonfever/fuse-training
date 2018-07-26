package com.test.lab04.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Consumes({MediaType.APPLICATION_JSON})
@Produces({MediaType.APPLICATION_JSON})
public interface APIService {

    @GET
    @Path("/mortgages/getMortgageApplication")
    public Response getMortgageApplication();

    @GET
    @Path("/applicants/getApplicant")
    public Response getApplicant();

    @GET
    @Path("/properties/getProperty")
    public Response getProperty();

}
