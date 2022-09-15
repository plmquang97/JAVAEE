package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.TermDAO;
import com.axonactive.agileterm.entity.TermEntity;
import com.axonactive.agileterm.rest.client.model.Term;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class TermDAOImpl implements TermDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Override
    public List<TermEntity> getAll() {
        return em.createQuery(
                "SELECT DISTINCT t FROM TermEntity t LEFT JOIN FETCH t.descriptionEntityList d")
                .getResultList();
    }

    @Override
    public TermEntity findTermById(Integer id) {
        return  em.createQuery(
                "SELECT DISTINCT t FROM TermEntity t LEFT JOIN FETCH t.descriptionEntityList d WHERE t.id = :id",TermEntity.class)
                .setParameter("id",id)
                .getSingleResult();

    }

    @Override
    public TermEntity save(TermEntity termEntity) {
        return this.em.merge(termEntity);
    }


}
