package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.TopicEntity;

import javax.ejb.Stateless;
import java.util.List;
@Stateless
public interface TermTopicDAO {
    List<TopicEntity> findListOfTopicByTermId(Integer id);
}
