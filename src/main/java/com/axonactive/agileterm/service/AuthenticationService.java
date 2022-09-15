package com.axonactive.agileterm.service;

import antlr.Token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.xml.registry.infomodel.User;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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



}
