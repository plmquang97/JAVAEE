package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.VerificationTokenDAO;
import com.axonactive.agileterm.entity.VerificationTokenEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class VerificationTokenDAOImpl implements VerificationTokenDAO {

    @PersistenceContext
    EntityManager em;

    @Override
    public VerificationTokenEntity save(VerificationTokenEntity verificationTokenEntity) {
        return this.em.merge(verificationTokenEntity);
    }
}
