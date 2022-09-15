package com.axonactive.agileterm.service;

import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.rest.client.model.Topic;
import com.axonactive.agileterm.rest.model.TopicDto;
import com.axonactive.agileterm.service.dao.TopicDAO;
import com.axonactive.agileterm.service.mapper.TopicMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TopicService {

    @Inject
    private TopicDAO topicDAO;

    @Inject
    private TopicMapper topicMapper;

    public List<TopicEntity> getAll() {
        return topicDAO.findAll();
    }

    public TopicEntity findById(Integer topicId) {
        return topicDAO.findById(topicId);
    }

    public TopicEntity save(Topic topicInput) {
        return topicDAO.save(topicInput);
    }


}
