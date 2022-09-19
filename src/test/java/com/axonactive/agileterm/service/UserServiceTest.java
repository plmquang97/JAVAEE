package com.axonactive.agileterm.service;

import com.axonactive.agileterm.dao.UserDAO;
import com.axonactive.agileterm.entity.UserEntity;
import com.axonactive.agileterm.entity.UserRoleAssignmentEntity;
import com.axonactive.agileterm.entity.VerificationTokenEntity;
import com.axonactive.agileterm.rest.client.model.User;
import com.axonactive.agileterm.service.mapper.UserMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;
    @Mock
    private UserDAO userDAO;

    @Mock
    private UserMapper userMapper;

    List<UserRoleAssignmentEntity> roleAssignments = new ArrayList<>();
    UserRoleAssignmentEntity role1 = new UserRoleAssignmentEntity();
    List<UserEntity> users = new ArrayList<>();

    UserEntity user1 = UserEntity.builder()
            .username("nhthinh")
            .password("12345")
            .email("thinh@gmail.com")
            .roles(null)
            .verificationTokenEntity(new VerificationTokenEntity())
            .build();

    UserEntity user2 = UserEntity.builder()
            .username("mquang")
            .password("12345")
            .email("quang@gmail.com")
            .roles(null)
            .verificationTokenEntity(new VerificationTokenEntity())
            .build();
    @BeforeEach
    void setUp(){
        users.add(user1);
        users.add(user2);
    }

    @Test
    void testGetAll_shouldReturnData_WhenUsed() {
        when(userDAO.getAll()).thenReturn(users);

        List<UserEntity> actualUsers = userService.getAll();

        assertEquals(users.size(),actualUsers.size());
    }

    @Test
    void testFindUserEntityByUserName_shouldReturnOneUser_WhenFound() {
        when(userDAO.findUserByUserName("mquang")).thenReturn(user2);
        assertEquals(user2,userService.findUserEntityByUserName("mquang"));
    }


    @Test
    void countUserWithUsername() {
    }

    @Test
    void countUserWithEmail() {
    }


    @Test
    void save() {
    }

    @Test
    void isExistingEmail() {
    }

    @Test
    void isExistingUsername() {
    }

    @Test
    void passwordMatchesValid() {
    }
}
