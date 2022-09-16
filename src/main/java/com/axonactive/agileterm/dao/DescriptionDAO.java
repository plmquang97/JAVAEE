package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.DescriptionEntity;
import com.axonactive.agileterm.rest.client.model.Description;

import javax.ejb.Stateless;

@Stateless
public interface DescriptionDAO {
    DescriptionEntity save(Integer termId,Description descriptionInput);
}

