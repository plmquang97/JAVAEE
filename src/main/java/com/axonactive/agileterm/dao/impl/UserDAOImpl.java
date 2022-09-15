package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserDAOImpl implements UserDAO {
    @PersistenceContext(name = "agileterm")
    EntityManager em;
    @Inject
    UserDAO userDAO;

    @Override
    public UserEntity findUserByUserName(String username) {
        return em.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username",UserEntity.class)
                .setParameter("userName",username)
                .getSingleResult();
    }

    @Override
    public UserEntity save(UserEntity userInput) {
        return userDAO.save(userInput);
    }
}
