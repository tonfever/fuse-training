package com.test.lab03.error;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.ext.ExceptionMapper;

import com.test.lab03.common.Constant;
import com.test.lab03.common.ResponseWrapper;

public class RestGenericExceptionMapper implements ExceptionMapper<Exception>{

    public Response toResponse(Exception ex) {
        ResponseWrapper response = new ResponseWrapper(Constant.RESPONSE_CODE_ERROR);
        response.addMessages(ex.getMessage());
        ResponseBuilder responseBuilder = Response.ok(response, MediaType.APPLICATION_JSON).status(Response.Status.INTERNAL_SERVER_ERROR);
        return responseBuilder.build();
    }

}