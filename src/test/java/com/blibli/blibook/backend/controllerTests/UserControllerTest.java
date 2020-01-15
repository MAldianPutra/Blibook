package com.blibli.blibook.backend.controllerTests;

import com.blibli.blibook.backend.controller.UserController;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.service.UserService;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    @Mock
    UserService userService;

    @Mock
    ObjectMapperServiceImpl objectMapperService;

    @InjectMocks
    UserController userController;

    private UserRole sampleUserRole = new UserRole(
            1,
            "ROLE_ADMIN",
            null
    );

    private UserStatus sampleUserStatus = new UserStatus(
            1,
            "AVAILABLE",
            null
    );

    private User sampleUser = new User(
            1,
            "testuser@gmail.com",
            "test_user",
            "password",
            "password",
            "01-01-2000",
            "08126107686",
            "L",
            null,
            null,
            null,
            sampleUserStatus,
            sampleUserRole
    );

    private UserDTO sampleUserDTO = new UserDTO(
            sampleUser.getUserId(),
            sampleUser.getUserName(),
            sampleUser.getUserEmail(),
            sampleUser.getUserBirthdate(),
            sampleUser.getUserGender(),
            sampleUser.getUserHandphone(),
            sampleUser.getUserRole().getUserRoleName(),
            sampleUser.getUserStatus().getUserStatusName()
    );

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        userController = new UserController();
        sampleUser = new User();
        userService = mock(UserService.class);
        objectMapperService = mock(ObjectMapperServiceImpl.class);
    }

    @Test
    void findByUserId() {
        Integer userId = 1;
        when(userService.findFirstByUserId(anyInt())).thenReturn(sampleUser);

        UserDTO userDTO = userController.findByUserId(userId);
        assertThat(userDTO.getUserId().equals(sampleUser.getUserId()));
        assertEquals(userDTO, objectMapperService.mapToUserDTO(sampleUser));
    }

    @Test
    void register() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void login() {
    }

    @Test
    void deleteByUserId() {
    }

    @Test
    void findAllWithPaging() {
    }

    @Test
    void findAll() {
    }

    @Test
    void blockUser() {
    }
}