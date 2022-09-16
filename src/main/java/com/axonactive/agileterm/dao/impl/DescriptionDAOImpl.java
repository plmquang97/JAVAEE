package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.DescriptionDAO;
import com.axonactive.agileterm.entity.DescriptionEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DescriptionDAOImpl implements DescriptionDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;

    @Override
    public DescriptionEntity save(DescriptionEntity descriptionEntity) {
        return this.em.merge(descriptionEntity);
    }

    @Override
    public List<DescriptionEntity> findDescriptionByTermIdAndDescriptionString(String termName, String descriptionString) {
        return em.createQuery("SELECT d FROM DescriptionEntity d WHERE LOWER(d.term.name) = LOWER(:termName) AND LOWER(d.content) = LOWER(:descriptionString)")
                .setParameter("termName",termName)
                .setParameter("descriptionString",descriptionString)
                .getResultList();
    }


}
