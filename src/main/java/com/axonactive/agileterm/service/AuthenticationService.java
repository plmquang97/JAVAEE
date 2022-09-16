package com.axonactive.agileterm.service;


import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.ResourceNotFoundException;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {
    @Inject
    private UserService userService;

    public boolean checkAuthentication(String username, String password) {

        UserEntity user = userService.findUserEntityByUserName(username);

        if (userService.findUserEntityByUserName(username) == null) {
            return false;
        }

        return BCrypt.checkpw(password,user.getPassword());
    }

}

