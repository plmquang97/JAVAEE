package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.TermTopicEntity;
import com.axonactive.agileterm.entity.TopicEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface TermTopicDAO {
    List<TopicEntity> findTopicByTermName(String name);

    List<TermEntity> findTermByTopicName(String name);

    List<TopicEntity> findTopicByTermId(Integer id);

    List<TermEntity> findTermByTopicId(Integer id);

    TermEntity findTermByTermId(Integer Id);
}
