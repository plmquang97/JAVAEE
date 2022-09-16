package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.exception.ResourceNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    public List<UserEntity> getAll(){
        return userDAO.getAll();
    }

    public Long countUserWithUsername(String username){
        return userDAO.countUsersWithUsername(username);
    }

    public Long countUserWithEmail(String email){
        return userDAO.countUsersWithEmail(email);
    }

    public UserEntity findUserEntityByUserName(String username){
        UserEntity userEntity = userDAO.findUserByUserName(username);
        if (userEntity == null){
            throw new ResourceNotFoundException("");
        }
        return userEntity;
    }

}
