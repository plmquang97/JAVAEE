package com.axonactive.agileterm.rest.api;

import com.axonactive.agileterm.service.UserService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

@Stateless
@Path(UserResource.PATH)
public class UserResource {

    public static final String PATH = "/users";

    @Inject
    UserService userService;


}
