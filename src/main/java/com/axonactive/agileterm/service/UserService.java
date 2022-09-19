package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.dao.VerificationTokenDAO;
import com.axonactive.agileterm.entity.Role;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.entity.UserRoleAssignmentEntity;
import com.axonactive.agileterm.entity.VerificationTokenEntity;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.ErrorMessage;
import com.axonactive.agileterm.exception.ResourceNotFoundException;
import com.axonactive.agileterm.rest.client.model.User;
import com.axonactive.agileterm.rest.model.UserDto;
import com.axonactive.agileterm.service.mapper.UserMapper;
import org.mindrot.jbcrypt.BCrypt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class UserService {

    @Inject
    private UserDAO userDAO;

    @Inject
    private UserMapper userMapper;
    @Inject
    private VerificationTokenDAO verificationTokenDAO;


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
            throw new ResourceNotFoundException(ErrorMessage.USER_NOT_FOUND);
//            throw new SecurityException(ErrorMessage.UNAUTHORIZED_ACCESS);

        }
        return userEntity;
    }

    public UserDto save(User userRequest){
        UserEntity createdUserEntity = new UserEntity();
        List<UserRoleAssignmentEntity> userRoleAssignmentEntityList = new ArrayList<>();
        userRoleAssignmentEntityList.add(new UserRoleAssignmentEntity(null, Role.ROLE_USER, createdUserEntity));
        createdUserEntity.setRoles(userRoleAssignmentEntityList);
        createdUserEntity.setUsername(userRequest.getUserName());

        String hashedPassword = BCrypt.hashpw(userRequest.getPassword(), BCrypt.gensalt(12));

        createdUserEntity.setPassword(hashedPassword);
        createdUserEntity.setEmail(userRequest.getEmail());
        createdUserEntity.setVerificationTokenEntity(verificationTokenDAO.save(new VerificationTokenEntity()));

        return userMapper.toDto(userDAO.save(createdUserEntity));
    }

}
