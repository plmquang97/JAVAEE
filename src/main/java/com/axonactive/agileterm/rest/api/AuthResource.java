package com.axonactive.agileterm.rest.api;


import com.axonactive.agileterm.dao.impl.UserDAOImpl;
import com.axonactive.agileterm.rest.client.model.JwtRequest;
import com.axonactive.agileterm.rest.client.model.User;
import com.axonactive.agileterm.rest.model.JwtResponse;
import com.axonactive.agileterm.rest.model.UserDto;
import com.axonactive.agileterm.service.UserService;
import com.axonactive.agileterm.utility.JwtUtils;
import org.hibernate.annotations.Parameter;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
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
    public Response save(@Valid User userRequest) {
        UserDto userDto = userService.save(userRequest);
        return Response.ok().entity(userDto).status(Response.Status.CREATED).build();
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public String getAuthenticationToken(@Valid JwtRequest jwtRequest) {
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
    public Response getJwtResponse(@Valid JwtRequest jwtRequest) {
        JwtResponse jwtResponse = jwtUtils.generateJwtResponse(jwtRequest);
        return Response.ok(jwtResponse).build();
    }

    @GET
    @Path("validate-username/{username}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response usernameExistValidate(@PathParam("username") String username) {
        userService.validateUserName(username);
        return Response.noContent().build();
    }

    @GET
    @Path("validate-email/{email}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response emailExistValidate(@PathParam("email") String email) {
        userService.validateEmail(email);
        return Response.noContent().build();
    }
}
