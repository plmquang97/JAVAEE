package com.axonactive.agileterm.service.dao.impl;
import com.axonactive.agileterm.service.TermTopicService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class TermTopicDAOImplTest {
    @InjectMocks
    private TermTopicService termTopicDAOService;

    @Test
    void findTopicByTermName() {
    }
}