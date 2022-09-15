package com.axonactive.agileterm.service.impl;

import com.axonactive.agileterm.entity.TermTopicEntity;
import com.axonactive.agileterm.dao.TermTopicDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TermTopicDAOImpl implements TermTopicDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Override
    public List<TermTopicEntity> findTopicByTermName(String name) {

        return em.createQuery("SELECT t.topic FROM TermTopicEntity t WHERE t.term.name = ?1", TermTopicEntity.class).getResultList();
    }
}
