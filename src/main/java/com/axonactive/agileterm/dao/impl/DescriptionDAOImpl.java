package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.DescriptionDAO;
import com.axonactive.agileterm.dao.TermDAO;
import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.DescriptionEntity;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.rest.client.model.Description;
import com.axonactive.agileterm.rest.client.model.Term;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class DescriptionDAOImpl implements DescriptionDAO {

    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Inject
    DescriptionDAO descriptionDAO;

    @Inject
    TermDAO termDAO;

    @Inject
    UserDAO userDAO;

    @Override
    public DescriptionEntity save(Integer termId, Description descriptionInput) {
        DescriptionEntity descriptionEntity = new DescriptionEntity();

        TermEntity term = termDAO.findTermById(termId);
        UserEntity user = userDAO.findByUserName(descriptionInput.getUserName());

        descriptionEntity.setContent(descriptionInput.getContent());
        descriptionEntity.setTerm(term);
        descriptionEntity.setUserEntity(user);

        return this.em.merge(descriptionEntity);
    }
}
