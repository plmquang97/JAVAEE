package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.TermDAO;
import com.axonactive.agileterm.entity.TermEntity;

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
                        "SELECT DISTINCT t FROM TermEntity t LEFT JOIN FETCH t.descriptionEntityList d LEFT JOIN FETCH d.userEntity u ")
                .getResultList();
    }

    @Override
    public TermEntity findTermById(Integer id) {
        List<TermEntity> termEntityList = em.createQuery(
                        "SELECT DISTINCT t FROM TermEntity t LEFT JOIN FETCH t.descriptionEntityList d " +
                                "LEFT JOIN FETCH d.userEntity u WHERE t.id = :id", TermEntity.class)
                .setParameter("id", id)
                .getResultList();
        if (!termEntityList.isEmpty())
            return termEntityList.get(0);
        return null;
    }

    @Override
    public TermEntity save(TermEntity termEntity) {
        return this.em.merge(termEntity);
    }

    @Override
    public TermEntity findTermByTermName(String name) {
        List<TermEntity> termEntityList = em.createQuery(
                        "SELECT DISTINCT t FROM TermEntity t LEFT JOIN FETCH t.descriptionEntityList d " +
                                "LEFT JOIN FETCH d.userEntity u WHERE LOWER(t.name) = LOWER(:inputName)", TermEntity.class)
                .setParameter("inputName", name)
                .getResultList();
        if (!termEntityList.isEmpty())
            return termEntityList.get(0);
        return null;
    }


}
