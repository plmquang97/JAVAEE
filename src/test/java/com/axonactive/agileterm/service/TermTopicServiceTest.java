package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermTopicDAO;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.entity.TermTopicEntity;
import com.axonactive.agileterm.entity.TopicEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class TermTopicServiceTest {
    @InjectMocks
    private TermTopicService termTopicService;
    @Mock
    private TermTopicDAO termTopicDAO;

    List<TopicEntity> topics = new ArrayList<>();
    TopicEntity topicAgile = new TopicEntity(1, "Agile", "#000000");
    TopicEntity topicScrum = new TopicEntity(2, "Scrum", "#ffffff");

    List<TermEntity> terms = new ArrayList<>();
    TermEntity termDod = new TermEntity(1, "dod", new ArrayList<>());
    TermEntity termAgile = new TermEntity(1, "agile", new ArrayList<>());

    TermTopicEntity termTopic1 =new TermTopicEntity(1, termDod, topicAgile);
    TermTopicEntity termTopic2 =new TermTopicEntity(1, termDod, topicScrum);
    TermTopicEntity termTopic3 =new TermTopicEntity(2, termDod, topicScrum);


    @BeforeEach
    void setUp() {
        topics.add(topicAgile);
        topics.add(topicScrum);

        terms.add(termDod);
        terms.add(termAgile);
    }

    @Test
    void findListOfTopicEntityFromTermId_shouldReturnData_whenFound() {
        when(termTopicDAO.findListOfTopicByTermId(1)).thenReturn(topics);

        assertEquals(topics.size(), termTopicService.findListOfTopicEntityFromTermId(1).size());
    }
}