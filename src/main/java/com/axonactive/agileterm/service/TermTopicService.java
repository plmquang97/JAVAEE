package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermTopicDAO;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.TermTopicEntity;
import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.rest.client.model.TermTopic;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TermTopicService {
    @Inject
    TermTopicDAO termTopicDAO;

    public List<TopicEntity> findTopicByTermName(String name){
        return termTopicDAO.findTopicByTermName(name);
    }

    public List<TermEntity> findTermByTopicName(String name) {
        return termTopicDAO.findTermByTopicName(name);
    }

    public List<TopicEntity> findTopicByTermId(Integer id) {
        return termTopicDAO.findTopicByTermId(id);
    }

    public List<TermEntity> findTermByTopicId(Integer id) {
        return termTopicDAO.findTermByTopicId(id);
    }

    public TermEntity findTermByTermId(Integer id) {
        return termTopicDAO.findTermByTermId(id);
    }

    public TermTopicEntity save(TermTopic input){
        return termTopicDAO.save(input);
    }
}
