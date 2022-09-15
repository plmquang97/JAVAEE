package com.axonactive.agileterm.rest.api;


import com.axonactive.agileterm.rest.client.model.Term;
import com.axonactive.agileterm.rest.model.TermDto;
import com.axonactive.agileterm.service.TermService;
import com.axonactive.agileterm.service.mapper.TermMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Stateless
@Path(TermResource.PATH)
public class TermResource {
    public static final String PATH = "/terms";

    @Inject
    private TermService termService;

    @Inject
    private TermMapper termMapper;

    @Context
    private UriInfo uriInfo;


    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(termMapper.toDtos(termService.getAll())).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findTermById(@PathParam(value = "id") Integer id) {
        return Response.ok(termMapper.toDto(termService.findTermByTermId(id))).build();
    }


//    @GetMapping("/{id}/topic")
//    public ResponseEntity<List<TopicDto>> findTopicByTermId(@PathVariable(value = "id") Integer id) {
//        return ResponseEntity.ok(termTopicService.findTopicByTermId(id));
//    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response save(@Valid Term term) {
        TermDto createdTerm = termMapper.toDto(termService.save(term));
        return Response.ok().entity(createdTerm).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") String encodeId, Term term) {
        return Response.ok(termService.update(encodeId, term)).build();
    }


// most important method
//    @GET
//    @Path("{encodedTermId}/details")
//
//    public Response getTermDetailById(@PathParam("encodedTermId") String encodedId) {
//        return Response.ok(termService.findTermDetailById(encodedId));
//    }


    //multipartFIle??
//    @PostMapping(value = "/upload-file", consumes = "multipart/form-data")
//    public ResponseEntity<ResponseForUploadFile> uploadTermWithExcelFile(@RequestParam("file") MultipartFile file) throws IOException {
//        return ResponseEntity.ok(termService.uploadTermAndDescriptionExcelFile(file));
//    }
    //DTO?
//    @PostMapping("existed-validate")
//    public ResponseEntity<Void> termExistValidate(@RequestBody TermName userInput) {
//        termService.validateNewTermName(userInput.getName());
//        return ResponseEntity.noContent().build();
//    }

}
