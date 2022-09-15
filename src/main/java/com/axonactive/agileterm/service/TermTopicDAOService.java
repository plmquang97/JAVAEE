package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.TermTopicDAO;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class TermTopicDAOService {
    @Inject
    TermTopicDAO termTopicDAO;

}
