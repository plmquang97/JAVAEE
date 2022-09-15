package com.axonactive.agileterm.service;


import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class AuthenticationService {

    @Inject
    UserService userService;

    public void checkAuthentication(String username, String password) throws Exception {

       if(userService.findByUserName(username) == null){
            //may add customException later
            throw new Exception("no such username");
        }
            UserEntity user = userService.findByUserName(username);

            if(!user.getPassword().equals(password)){
                //may add customException later
                throw new Exception("incorrect password");
            }
            else return;}

    }

