package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.TermTopicEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public interface TermTopicDAO {
    List<TermTopicEntity> findTopicByTermName(String name);
}
