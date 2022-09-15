package com.axonactive.agileterm.utility;

import antlr.Token;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.service.AuthenticationService;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.UUID;

@Stateless
public class JwtUtils {

    @EJB
    private AuthenticationService authenticationService;

    public Token generateJwtToken(UserEntity user) throws Exception{

            authenticationService.checkAuthentication(user.getUsername(), user.getPassword());

            String token = null;
            String secretKey = "agileterm";

            int timeToLive = 10800000;

            try {
                Algorithm algorithm = Algorithm.HMAC512(secretKey);

                token = JWT.create()
                        .withIssuedAt(new Date())
                        .withJWTId(UUID.randomUUID().toString())
                        .withClaim("username", user.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + timeToLive))
                        .sign(algorithm);

            } catch (IllegalArgumentException | JWTCreationException | UnsupportedEncodingException e) {
                //may add some log here
                System.out.println(e.getMessage());

            }

        if (token == null) {
                //may need to add custom exception
                throw new Exception("cant generate token");
            }
            return new Token(timeToLive, token);
        }
    }
