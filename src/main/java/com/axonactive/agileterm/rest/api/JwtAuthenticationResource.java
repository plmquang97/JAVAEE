package com.axonactive.agileterm.rest.api;


import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.rest.client.model.JwtRequest;
import com.axonactive.agileterm.rest.model.Token;
import com.axonactive.agileterm.utility.JwtUtils;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Stateless
@Path(JwtAuthenticationResource.PATH)
public class JwtAuthenticationResource {

    public static final String PATH = "/auth";

    @Inject
    private JwtUtils jwtUtils;

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Token getAuthenticationToken(@Valid JwtRequest jwtRequest){
        Token token = null;
        try {
            token = jwtUtils.generateJwtToken(jwtRequest);
        } catch (Exception e) {
           //may add some log here
            System.out.println(e.getMessage());
        }
        return token;
    }
}
