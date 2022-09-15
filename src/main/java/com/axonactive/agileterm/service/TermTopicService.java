package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermTopicDAO;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.TermTopicEntity;
import com.axonactive.agileterm.entity.TopicEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class TermTopicService {
    @Inject
    TermTopicDAO termTopicDAO;

    public List<TermTopicEntity> findTopicByTermName(String name){
//        return termTopicDAO.findTopicByTermName(name);
        return null;
    }

    public List<TermEntity> findTermByTopicName(String name) {
        return null;
    }

    public List<TopicEntity> findTopicByTermId(Integer id) {
        return null;
    }

    public List<TermEntity> findTermByTopicId(Integer id) {
        return null;
    }

    public TermEntity findTermByTermId(Integer Id) {
        return null;
    }
}
