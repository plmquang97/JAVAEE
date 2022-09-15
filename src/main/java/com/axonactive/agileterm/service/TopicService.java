package com.axonactive.agileterm.service;

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

    public List<TopicDto> getAll() {
        return topicMapper.toDtos(topicDAO.findAll());
    }

    public TopicDto findById(Integer topicId) {
        return topicMapper.toDto(topicDAO.findById(topicId));
    }
}
