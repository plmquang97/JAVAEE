package com.axonactive.agileterm.rest.api;

import com.axonactive.agileterm.rest.client.model.Topic;
import com.axonactive.agileterm.rest.model.TopicDto;
import com.axonactive.agileterm.service.TopicService;
import com.axonactive.agileterm.service.mapper.TopicMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
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

    @GET
    @Path("{TopicId}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findById(@PathParam("TopicId") Integer topicId) {
        return Response.ok(topicMapper.toDto(topicService.findById(topicId))).build();
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response save(@Valid Topic topic) {
        TopicDto createdTopic = topicMapper.toDto(topicService.save(topic));
        return Response.ok().entity(createdTopic).status(Response.Status.CREATED).build();
    }



}
