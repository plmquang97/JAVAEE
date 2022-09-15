package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.dao.impl.TermTopicDAOImpl;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.rest.client.model.Description;
import com.axonactive.agileterm.rest.client.model.Term;
import com.axonactive.agileterm.rest.client.model.TermTopic;
import com.axonactive.agileterm.rest.client.model.Topic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TermTopicServiceTest {

    @InjectMocks
    private TermTopicService termTopicDAOService;

    @Mock
    private TopicService topicService;
    @Mock
    private TermService termService;
    @Mock
    private DescriptionService descriptionService;
    @Mock
    private UserDAO userDAO;
    @Mock
    private TermTopicDAOImpl termTopicDAOImpl;

    @BeforeEach
    void setup(){
        Term newTermDOD = Term.builder()
                .name("DOD")
                .build();
        termService.save(newTermDOD);
        Term newTerm1 = Term.builder()
                .name("Daily Scrum")
                .build();
        termService.save(newTerm1);

        topicService.save(new Topic("Agile", "#ffffff"));
        topicService.save(new Topic("Scrum", "#000000"));
        topicService.save(new Topic("Process", "#ffcb00"));

        System.out.println(topicService.findById(1));


        termTopicDAOService.save(new TermTopic(1, 1));
        termTopicDAOService.save(new TermTopic(1, 2));
        termTopicDAOService.save(new TermTopic(1, 3));

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUsername("Huy");
        userEntity1.setEmail("admin@axonactive.com.vn");
        userEntity1.setPassword("Aavn123!@#");

        userDAO.save(userEntity1);
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUsername("Quang");
        userEntity2.setEmail("admn@axonactive.com.vn");
        userEntity2.setPassword("Aavn123!@#");
        userDAO.save(userEntity2);

        descriptionService.save(1, new Description("A set of conditions that software must meet in order to be accepted by a customer or stakeholder", "Huy"));

        descriptionService.save(1, new Description("A set of conditions that software must meet in order to be accepted by a customer or stakeholder", "Huy"));
    }

    @Test
    void findTopicByTermName() {
    }

    @Test
    void findTermByTopicName() {
    }

    @Test
    void findTopicByTermId() {
    }

    @Test
    void testFindTermByTopicId_shouldReturn1_whenInput1AndCheckSize() {
//        assertEquals(1, termTopicDAOService.findTermByTopicId(1).size());
        System.out.println(topicService.findById(1));
    }

    @Test
    void findTermByTermId() {
    }

    @Test
    void save() {
    }
}