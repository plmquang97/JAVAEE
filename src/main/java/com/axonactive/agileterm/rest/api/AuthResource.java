package com.axonactive.agileterm.rest.api;


import com.axonactive.agileterm.rest.client.model.JwtRequest;
import com.axonactive.agileterm.rest.client.model.User;
import com.axonactive.agileterm.rest.model.JwtResponse;
import com.axonactive.agileterm.rest.model.UserDto;
import com.axonactive.agileterm.service.UserService;
import com.axonactive.agileterm.utility.JwtUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public String getAuthenticationToken(@Valid JwtRequest jwtRequest){
           String token = null;

           //this check authen user and generate token
            token = jwtUtils.generateToken(jwtRequest);

        return token;
    }

    //login
    @POST
    @Path("login")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getJwtResponse(@Valid JwtRequest jwtRequest){
        JwtResponse jwtResponse = jwtUtils.generateJwtResponse(jwtRequest);
       return Response.ok(jwtResponse).build();
    }
}
