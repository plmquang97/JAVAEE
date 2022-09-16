package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.rest.client.model.Topic;

import java.util.List;

public interface TopicDAO {
    List<TopicEntity> findAll();

    TopicEntity save(Topic topic);


    TopicEntity findById(Integer topicId);

}
