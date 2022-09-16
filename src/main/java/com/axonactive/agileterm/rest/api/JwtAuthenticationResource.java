package com.axonactive.agileterm.rest.api;

import antlr.Token;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.rest.client.model.JwtResponse;
import com.axonactive.agileterm.utility.JwtUtils;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path(JwtAuthenticationResource.PATH)
public class JwtAuthenticationResource {

    public static final String PATH = "/auth";

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Token getAuthenticationToken(@Valid UserEntity user){
        Token token = null;
        try {
            token = jwtUtils.generateJwtToken(user);
        } catch (Exception e) {
           //may add some log here
            System.out.println(e.getMessage());
        }
        return token;
    }
}
