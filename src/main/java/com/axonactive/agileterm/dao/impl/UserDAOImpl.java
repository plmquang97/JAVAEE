package com.axonactive.agileterm.dao.impl;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserDAOImpl implements UserDAO {

    @PersistenceContext(name ="agileterm")
    EntityManager em;

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
    public UserEntity findByUserName(String userName) {
        return em.createQuery(
                "SELECT DISTINCT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                .setParameter("username", userName)
                .getSingleResult();
    }

    @Override
    public List<UserEntity> getAll() {
        return em.createQuery(
                "SELECT u FROM UserEntity u", UserEntity.class)
                .getResultList();
    }

}
