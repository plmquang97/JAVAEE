package com.axonactive.agileterm.dao;

import com.axonactive.agileterm.entity.UserEntity;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Optional;

@Stateless
public interface UserDAO {

    Long countUsersWithEmail(String email);

    Long countUsersWithUsername(String username);

    UserEntity findByUserName(String userName);

    List<UserEntity> getAll();

//   UserEntity findByVerificationTokenEntityVerificationCode(String code);
}