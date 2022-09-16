package com.axonactive.agileterm.service;


import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {
    @Inject
    private UserService userService;

    public boolean checkAuthentication(String username, String password) throws Exception {

            UserEntity user = userService.findUserEntityByUserName(username);
            return user.getPassword().equals(password);
    }
}


