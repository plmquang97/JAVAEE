package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermDAO;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.exception.InputValidation;
import com.axonactive.agileterm.exception.ResourceNotFoundException;
import com.axonactive.agileterm.rest.client.model.Term;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Base64;
import java.util.List;

@Stateless
public class TermService {
    @Inject
    private TermDAO termDAO;

    @Inject
    private DescriptionService descriptionService;

    private Integer getDecodedId(String encodedId) {
        if (encodedId.length() < 2) {
            throw new InputValidation();
        }
        String decodedString = new String(Base64.getDecoder().decode(encodedId));
        return Integer.parseInt(decodedString.substring(decodedString.lastIndexOf('_') + 1));
    }

    public List<TermEntity> getAll() {
        return termDAO.getAll();
    }

    public TermEntity findTermByTermId(Integer id) {
        TermEntity termEntity = termDAO.findTermById(id);
        if (termEntity == null) {
            throw new ResourceNotFoundException("");
        }
        return termEntity;
    }

    public TermEntity save(Term term) {
        TermEntity termEntity = new TermEntity();
        termEntity.setName(termEntity.getName());
        if (descriptionService.descriptionListValidation(term.getDescriptionList()))
            termEntity.setDescriptionEntityList(
                    descriptionService
                            .convertListOfDescriptionToDescriptionEntity(
                                    term.getDescriptionList()));

        return termDAO.save(termEntity);
    }


    public TermEntity update(String encodeId, Term term) {
        Integer id = getDecodedId(encodeId);

        TermEntity termEntity = termDAO.findTermById(id);
        termEntity.setName(termEntity.getName());
        if (descriptionService.descriptionListValidation(term.getDescriptionList()))
            termEntity.setDescriptionEntityList(
                    descriptionService
                            .convertListOfDescriptionToDescriptionEntity(
                                    term.getDescriptionList()));
        return termDAO.save(termEntity);
    }


}
