package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.TermTopicDAO;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.TopicEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TermTopicDAOImpl implements TermTopicDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Override
    public List<TopicEntity> findTopicByTermName(String name) {
        return em.createQuery("SELECT t.topic FROM TermTopicEntity t WHERE t.term.name = ?1", TopicEntity.class).getResultList();
    }

    @Override
    public List<TermEntity> findTermByTopicName(String name) {
        return em.createQuery("SELECT DISTINCT t.term FROM TermTopicEntity t LEFT JOIN FETCH t.term.descriptionEntityList d WHERE t.topic.name = ?1").getResultList();
    }

    @Override
    public List<TopicEntity> findTopicByTermId(Integer id) {
        return em.createQuery("SELECT t.topic FROM TermTopicEntity t WHERE t.term.id = ?1").getResultList();
    }

    @Override
    public List<TermEntity> findTermByTopicId(Integer id) {
        return em.createQuery("SELECT DISTINCT t.term FROM TermTopicEntity t LEFT JOIN FETCH t.term.descriptionEntityList d WHERE t.topic.id = ?1").getResultList();
    }

    @Override
    public TermEntity findTermByTermId(Integer id) {
        return em.createQuery("SELECT DISTINCT t FROM TermEntity t LEFT JOIN FETCH t.descriptionEntityList d WHERE t.id = :id", TermEntity.class).setParameter("id", id).getSingleResult();
    }
}
