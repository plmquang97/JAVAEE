package com.axonactive.agileterm.rest.api;


import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.rest.client.model.Term;
import com.axonactive.agileterm.rest.model.TermDto;
import com.axonactive.agileterm.service.TermService;
import com.axonactive.agileterm.service.TermTopicService;
import com.axonactive.agileterm.service.mapper.TermMapper;
import lombok.extern.slf4j.Slf4j;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.json.bind.annotation.JsonbDateFormat;
import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.net.URI;

@Stateless
@Path(TermResource.PATH)
@MultipartConfig(fileSizeThreshold=1024*1024*10, // 10MB
        maxRequestSize=1024*1024*10)// 10MB
@Slf4j
public class TermResource {
    public static final String PATH = "/terms";

    @Inject
    private TermService termService;
    @Inject
    private TermMapper termMapper;

    @Inject
    private TermTopicService termTopicService;

    @Context
    private UriInfo uriInfo;




    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(termMapper.toDtos(termService.getAll())).build();
    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response save(@Valid Term term) {
        TermDto createdTerm = termMapper.toDto(termService.save(term));
        return Response.created(URI.create(PATH+"/"+createdTerm.getEncodedId())).entity(createdTerm).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") String encodeId, Term term) {
        return Response.ok(termMapper.toDto(termService.update(encodeId, term))).build();
    }


    @GET
    @Path("{encodedTermId}/details")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getTermDetailById(@PathParam("encodedTermId") String encodedId) {
        return Response.ok(termService.findTermDetailById(encodedId)).build();
    }

    @POST
    @Path("/upload-file")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces({MediaType.APPLICATION_JSON})
    public Response uploadTermWithExcelFile(MultipartFormDataInput excelFile) throws IOException {
        return Response.ok(termService.uploadTermAndDescriptionExcelFile(excelFile)).build();
    }



}
