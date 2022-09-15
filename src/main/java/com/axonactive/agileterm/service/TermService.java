package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermDAO;
import com.axonactive.agileterm.rest.client.model.Term;
import com.axonactive.agileterm.rest.model.TermDto;
import com.axonactive.agileterm.service.mapper.TermMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TermService {
    @Inject
    private TermDAO termDAO;
    @Inject
    private TermMapper termMapper;

    public List<TermDto> getAll() {
    return termMapper.toDtos(termDAO.getAll());
    }

    public TermDto findTermByTermId(Integer id) {
        return termMapper.toDto(termDAO.findTermById(id));
    }

//    public Term save(Term term) {
//
//    }
//
//    public TermDto update(Integer id, Term term) {
//    }
}
