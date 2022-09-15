package com.axonactive.agileterm.rest.api;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Path;

@Stateless
@Path(TopicResource.PATH)
public class TopicResource {
    public static final String PATH = "topics";


}
