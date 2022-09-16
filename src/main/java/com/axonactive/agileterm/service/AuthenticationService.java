package com.axonactive.agileterm.service;


import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.ResourceNotFoundException;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {
    @Inject
    private UserService userService;

    public void checkAuthentication(String username, String password) throws Exception {

        UserEntity user = userService.findUserEntityByUserName(username);

        if (userService.findUserEntityByUserName(username) == null) {
            //may add customException later
            throw new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND);
        }
        if (!user.getPassword().equals(password)) {
            //may add customException later
            throw new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND);
        } else return;
    }

}

