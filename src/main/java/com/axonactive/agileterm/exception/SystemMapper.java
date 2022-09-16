package com.axonactive.agileterm.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class SystemMapper implements ExceptionMapper<SystemException> {
    @Override
    public Response toResponse(SystemException e) {
        String message = e.getMessage();

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(message)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }
}
