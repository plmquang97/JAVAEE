package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TopicDAO;
import com.axonactive.agileterm.entity.TopicEntity;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.ResourceNotFoundException;
import com.axonactive.agileterm.rest.client.model.Topic;
import com.axonactive.agileterm.service.mapper.TopicMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
public class TopicServiceTest {
    @InjectMocks
    private TopicService topicService;

    @Mock
    private TopicDAO topicDAO;

    @Mock
    private TopicMapper topicMapper;

    List<TopicEntity> topics = new ArrayList<>();

    TopicEntity topic1 = new TopicEntity(1, "Agile", "#000000");
    TopicEntity topic2 = new TopicEntity(2, "Scrum", "#ffffff");

    @BeforeEach
    void setUp() {
        topics.add(topic1);
        topics.add(topic2);
    }

    @Test
    void testGetAll_shouldReturnData_whenUsed() {
        when(topicDAO.findAll()).thenReturn(topics);

        List<TopicEntity> actualTopics = topicService.getAll();

        assertEquals(topics.size(), actualTopics.size());
    }

    @Test
    void testFindById_shouldReturn1_whenInput1() {
        when(topicDAO.findById(1)).thenReturn(topic1);

        TopicEntity actualTopic = topicService.findTopicById(1);

        assertEquals(actualTopic.getName(), topic1.getName());
        assertEquals(actualTopic.getColor(), topic1.getColor());
    }

    @Test
    void testFindById_shouldThrowResourceNotFoundException_whenInput3() {
        when(topicDAO.findById(100)).thenThrow(new ResourceNotFoundException(ErrorMessage.TOPIC_NOT_FOUND));

        assertThrows(ResourceNotFoundException.class, () -> {
            topicService.findTopicById(100);
        });

    }

    @Test
    void testSave_sizeShouldIncrease_whenSaveNewRequest() {
        TopicEntity topic3 = new TopicEntity(3, "Clean code", "#112233");
        Topic topicRequest3 = new Topic("Clean code", "#112233");
        when(topicDAO.save(topicRequest3)).thenReturn(topic3);

        TopicEntity expectedTopic = topicService.save(topicRequest3);

        assertEquals(expectedTopic.getName(), topicRequest3.getName());

    }


}
