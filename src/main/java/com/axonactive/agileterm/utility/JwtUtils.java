package com.axonactive.agileterm.utility;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.axonactive.agileterm.entity.Role;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.entity.UserRoleAssignmentEntity;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.ResourceNotFoundException;
import com.axonactive.agileterm.rest.client.model.JwtRequest;
import com.axonactive.agileterm.rest.model.JwtResponse;
import com.axonactive.agileterm.service.AuthenticationService;
import com.axonactive.agileterm.service.UserService;
import lombok.Value;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Stateless
public class JwtUtils {

    @Inject
    private AuthenticationService authenticationService;
    @Inject
    private UserService userService;

    public String generateToken(@Valid JwtRequest jwtRequest) {

        if (!authenticationService.checkAuthentication(jwtRequest.getUsername(), jwtRequest.getPassword())) {
            throw new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND);
//            throw new SecurityException(ErrorMessage.UNAUTHORIZED_ACCESS);
        }

        String token = null;
        String secretKey = "agileterm";
        String issuer = "teamuar";

        int timeToLive = 10800000;

        Algorithm algorithm = Algorithm.HMAC512(secretKey);

        token = JWT.create()
                .withIssuer(issuer)
                .withIssuedAt(new Date())
                .withJWTId(UUID.randomUUID().toString())
                .withClaim("username", jwtRequest.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + timeToLive))
                .sign(algorithm);

        return token;
    }

    public JwtResponse generateJwtResponse(@Valid JwtRequest jwtRequest){

        String token = generateToken(jwtRequest);

        String username = jwtRequest.getUsername();

        UserEntity user = userService.findUserEntityByUserName(username);

        List<Role> roleList = user.getRoles().stream()
                .map(UserRoleAssignmentEntity::getRole)
                .collect(Collectors.toList());

        Boolean isActive = user.getActivated();

        return new JwtResponse(token, username, roleList, isActive);

    }
}
