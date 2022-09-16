package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.rest.client.model.Term;

import javax.ejb.Stateless;
import java.util.Optional;
import java.util.List;

@Stateless
public interface TermDAO {
    List<TermEntity> getAll();

    TermEntity findTermById(Integer id);

    TermEntity save(TermEntity termEntity);

    TermEntity findTermByTermName(String name);

}
