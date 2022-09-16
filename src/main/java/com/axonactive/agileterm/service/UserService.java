package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.exception.ResourceNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {
    @Inject
    private UserDAO userDAO;

    public UserEntity findUserEntityByUserName(String username){
        UserEntity userEntity = userDAO.findUserByUserName(username);
        if (userEntity == null){
            throw new ResourceNotFoundException("");
        }
        return userEntity;
    }

}
