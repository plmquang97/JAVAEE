package com.axonactive.agileterm.rest.api;

import com.axonactive.agileterm.service.TopicService;
import com.axonactive.agileterm.service.mapper.TopicMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path(TopicResource.PATH)
public class TopicResource {
    public static final String PATH = "topics";

    @Inject
    TopicService topicService;

    @Inject
    TopicMapper topicMapper;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(topicMapper.toDtos(topicService.getAll())).build();
    }






}
