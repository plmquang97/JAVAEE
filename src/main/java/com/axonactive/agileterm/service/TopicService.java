package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TopicDAO;
import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.ResourceNotFoundException;
import com.axonactive.agileterm.rest.client.model.Topic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TopicService {
    @Inject
    private TopicDAO topicDAO;

    public List<TopicEntity> getAll() {
        return topicDAO.findAll();
    }

    public TopicEntity findTopicById(Integer topicId) {
        TopicEntity topicEntity = topicDAO.findById(topicId);
        if (topicEntity == null) {
            throw new ResourceNotFoundException(ErrorMessage.TOPIC_NOT_FOUND);
        }
        return topicEntity;
    }

    public TopicEntity save(Topic topicInput) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setName(topicInput.getName());
        topicEntity.setColor(topicInput.getColor());
        return topicDAO.save(topicEntity);
    }


}
