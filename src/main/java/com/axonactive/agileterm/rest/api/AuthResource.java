package com.axonactive.agileterm.rest.api;


import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.rest.client.model.JwtRequest;
import com.axonactive.agileterm.rest.client.model.User;
import com.axonactive.agileterm.rest.model.Token;
import com.axonactive.agileterm.rest.model.UserDto;
import com.axonactive.agileterm.service.UserService;
import com.axonactive.agileterm.utility.JwtUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path(AuthResource.PATH)
public class AuthResource {

    public static final String PATH = "/auth";

    @Inject
    private JwtUtils jwtUtils;

    @Inject
    private UserService userService;

    @POST
    @Path("/signup")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response save(@Valid User userRequest){
        UserDto userDto = userService.save(userRequest);
        return Response.ok().entity(userDto).status(Response.Status.CREATED).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Token getAuthenticationToken(@Valid JwtRequest jwtRequest){
           Token token = null;

            token = jwtUtils.generateJwtToken(jwtRequest);

        return token;
    }
}
