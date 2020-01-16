package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.service.UserService;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class UserControllerTest {

    @Spy
    @InjectMocks
    UserController userController;

    @Mock
    UserService userService;

    @Mock
    ObjectMapperServiceImpl objectMapperService;

    @Mock
    ObjectMapper objectMapper;

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
            null,
            null
    );

    private UserDTO sampleUserDTO = new UserDTO(
            sampleUser.getUserId(),
            sampleUser.getUserName(),
            sampleUser.getUserEmail(),
            sampleUser.getUserBirthdate(),
            sampleUser.getUserGender(),
            sampleUser.getUserHandphone(),
            null,
            null
    );

    private ResponseDTO sampleResponseDTO = new ResponseDTO(
            200,
            "Success.",
            null
    );

    @Test
    void findByUserId() {
        // Given
        when(userService.findFirstByUserId(anyInt())).thenReturn(sampleUser);
        when(objectMapperService.mapToUserDTO(sampleUser)).thenReturn(sampleUserDTO);

        // When
        UserDTO testedUserDTO = userController.findByUserId(anyInt());

        // Assert
        assertEquals(sampleUserDTO, testedUserDTO);
    }

    @Test
    void register() throws IOException {
        // Given
        when(userService.register(any(User.class))).thenReturn(sampleResponseDTO);
        doReturn(sampleUser).when(userController).mapToUser(anyString(), anyInt(), anyInt());

        // When
        ResponseDTO testedResponseDTO = userController.register(anyString(), anyInt(), anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void updateUser() throws IOException {
        // Given
        when(userService.updateUser(any(User.class))).thenReturn(sampleResponseDTO);
        doReturn(sampleUser).when(userController).mapToUser(anyString(), anyInt(), anyInt());

        // When
        ResponseDTO testedResponseDTO = userController.updateUser(anyString(), anyInt(), anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void login() {
        // Given
        when(userService.login(anyString(), anyString())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = userController.login(anyString(), anyString());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void deleteByUserId() {
        // Given
        when(userService.deleteByUserID(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = userController.deleteByUserId(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAll() {
        // Given
        when(userService.findAll()).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = userController.findAll();

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void blockUser() {
        // Given
        when(userService.blockByUserId(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = userController.blockUser(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void mapToUser() {
    }
}