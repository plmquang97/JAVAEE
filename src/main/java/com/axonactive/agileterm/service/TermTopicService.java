package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermTopicDAO;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.TopicEntity;
import org.apache.xmlbeans.impl.xb.xsdschema.ListDocument;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TermTopicService {
    @Inject
    TermTopicDAO termTopicDAO;

    public List<TopicEntity> findListOfTopicEntityFromTermId(Integer id){
        return termTopicDAO.findListOfTopicByTermId(id);
    }
}
