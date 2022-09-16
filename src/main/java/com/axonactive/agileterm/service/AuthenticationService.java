package com.axonactive.agileterm.service;


import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {
    @Inject
    private UserService userService;

    public void checkAuthentication(String username, String password) throws Exception {

       if(userService.findUserEntityByUserName(username) == null){
            //may add customException later
            throw new Exception("no such username");
        }
            UserEntity user = userService.findUserEntityByUserName(username);

            if(!user.getPassword().equals(password)){
                //may add customException later
                throw new Exception("incorrect password");
            }
            else return;}

    }

