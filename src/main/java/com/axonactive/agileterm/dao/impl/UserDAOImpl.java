package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(name = "agileterm")
    EntityManager em;
    @Inject
    UserDAO userDAO;

    @Override
    public UserEntity findUserByUserName(String username) {
        List<UserEntity> userEntityList = em.createQuery(
                "SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username",username)
                .getResultList();
        if(!userEntityList.isEmpty()){
            return userEntityList.get(0);
        }
        return null;
    }

    @Override
    public Long countUsersWithEmail(String email) {
        return em.createQuery(
                "SELECT COUNT(u.id) FROM UserEntity u WHERE u.email = :email", Long.class)
                .setParameter("email", email)
                .getSingleResult();
    }

    @Override
    public Long countUsersWithUsername(String username) {
        return em.createQuery(
                "SELECT COUNT(u.id) FROM UserEntity u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();

    }

    @Override
    public List<UserEntity> getAll() {
        return em.createQuery(
                "SELECT u FROM UserEntity u", UserEntity.class)
                .getResultList();
    }

    public UserEntity save(UserEntity userInput) {
        return this.em.merge(userInput);
    }
}
