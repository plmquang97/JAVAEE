package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermTopicDAO;
import com.axonactive.agileterm.entity.TopicEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TermTopicService {
    @Inject
    TermTopicDAO termTopicDAO;

    public List<TopicEntity> findListOfTopicEntityFromTermId(Integer id){
        return termTopicDAO.findListOfTopicByTermId(id);
    }
}
