package com.axonactive.agileterm.service.dao;

import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.rest.client.model.Topic;

import java.util.List;

public interface TopicDAO {
    List<TopicEntity> findAll();

    TopicEntity save(Topic topic);

    void flush();

    TopicEntity findById(Integer topicId);

}
