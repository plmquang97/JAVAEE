package com.axonactive.agileterm.rest.api;


import com.axonactive.agileterm.rest.client.model.Term;
import com.axonactive.agileterm.service.TermService;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Stateless
@Path(TermResource.PATH)
public class TermResource {
    public static final String PATH = "/terms";

    @Inject
    private TermService termService;

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() {
        return Response.ok(termService.getAll()).build();
    }

    @GET
    @Path("{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findTermById(@PathParam(value = "id") Integer id) {
        return Response.ok(termService.findTermByTermId(id)).build();
    }


//    @GetMapping("/{id}/topic")
//    public ResponseEntity<List<TopicDto>> findTopicByTermId(@PathVariable(value = "id") Integer id) {
//        return ResponseEntity.ok(termTopicService.findTopicByTermId(id));
//    }


    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response save(Term term) {
        Term createdTerm = termService.save(term);
        return Response.ok().entity(createdTerm).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response update(@PathParam("id") Integer id, Term term) {
        return Response.ok(termService.update(id, term));
    }


// most important method
//    @GET
//    @Path("{encodedTermId}/details")
//    @Produces({MediaType.APPLICATION_JSON})
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
