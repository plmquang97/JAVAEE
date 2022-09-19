package com.axonactive.agileterm.rest.api;

import com.axonactive.agileterm.rest.client.model.User;
import com.axonactive.agileterm.rest.model.UserDto;
import com.axonactive.agileterm.service.UserService;
import com.axonactive.agileterm.service.mapper.UserMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;

@Stateless
@Path(UserResource.PATH)
public class UserResource {

    public static final String PATH = "/users";

    @Inject
    UserService userService;

    @Inject
    UserMapper userMapper;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll(){
        return Response.ok(userMapper.toDtos(userService.getAll())).build();
    }

    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    public Response save(@Valid User userRequest){
        UserDto createdUser = userService.save(userRequest);
        return Response.created(URI.create(PATH + "/"+createdUser.getId())).entity(createdUser).status(Response.Status.CREATED).build();
    }

}
