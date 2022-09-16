package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.TermTopicDAO;
import com.axonactive.agileterm.entity.TopicEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TermTopicDAOImpl implements TermTopicDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Override
    public List<TopicEntity> findListOfTopicByTermId(Integer id) {
        return em.createQuery("SELECT t.topic FROM TermTopicEntity t WHERE t.term.id = :id", TopicEntity.class).setParameter("id",id).getResultList();
    }
}
