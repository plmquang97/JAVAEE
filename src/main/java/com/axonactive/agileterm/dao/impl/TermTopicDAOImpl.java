package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.TermTopicDAO;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class TermTopicDAOImpl implements TermTopicDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

}
