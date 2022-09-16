package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;

@Stateless
public interface UserDAO {
    UserEntity findUserByUserName(String userName);
}
