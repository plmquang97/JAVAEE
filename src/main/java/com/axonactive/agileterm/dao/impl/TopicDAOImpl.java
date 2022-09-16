package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.TopicDAO;
import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.rest.client.model.Topic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.rest.client.model.Topic;
import com.axonactive.agileterm.dao.TopicDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class TopicDAOImpl implements TopicDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Inject
    TopicDAO topicDAO;


    @Override
    public List<TopicEntity> findAll() {
        Query allTopicQuery = em.createQuery("SELECT t FROM TopicEntity t", TopicEntity.class);
        return allTopicQuery.getResultList();
    }

    @Override
    public TopicEntity save(Topic topic) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setName(topic.getName());
        topicEntity.setColor(topic.getColor());
        topicEntity = this.em.merge(topicEntity);
        return topicEntity;
    }

    @Override
    public TopicEntity findById(Integer topicId) {
        return em.createQuery("SELECT t FROM TopicEntity t WHERE t.id = :topicId", TopicEntity.class)
                .setParameter("topicId", topicId).getSingleResult();
    }

}
