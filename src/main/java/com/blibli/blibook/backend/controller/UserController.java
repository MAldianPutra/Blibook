package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.blibli.blibook.backend.service.UserService;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ObjectMapperServiceImpl objectMapperService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping(ApiPath.USER)
    public UserDTO findByUserId(@RequestParam ("id") Integer userId) {
        User user = userService.findFirstByUserId(userId);
        return objectMapperService.mapToUserDTO(user);
    }


    @PostMapping(ApiPath.USER_REGISTER)
    public ResponseDTO register(@RequestParam ("user") String userData,
                                @RequestParam ("role") Integer role,
                                @RequestParam ("status") Integer status) throws IOException {
        User user = mapToUser(userData, role, status);
        return userService.register(user);
    }

    @PutMapping(ApiPath.USER_UPDATE)
    public ResponseDTO updateUser(@RequestParam ("user") String userData,
                                  @RequestParam ("role") Integer role,
                                  @RequestParam ("status") Integer status) throws IOException {
        User user = mapToUser(userData, role, status);
        return userService.updateUser(user);
    }

    @PostMapping(ApiPath.USER_LOGIN)
    public ResponseDTO login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.login(email, password);
    }

    @DeleteMapping(ApiPath.USER_DELETE)
    public ResponseDTO deleteByUserId(@RequestParam ("id") Integer userId){
        return userService.deleteByUserID(userId);
    }

    @GetMapping(ApiPath.USER_ALL)
    public ResponseDTO findAll(){
        return userService.findAll();
    }

    @PutMapping(ApiPath.USER_BLOCK)
    public ResponseDTO blockUser(@RequestParam("id") Integer userId){
        return userService.blockByUserId(userId);
    }

    User mapToUser(String userData, Integer role, Integer status) throws IOException {
        User user = objectMapper.readValue(userData, User.class);
        Optional<UserRole> userRole = userService.findUserRoleId(role);
        userRole.ifPresent(user::setUserRole);
        Optional<UserStatus> userStatus = userService.findUserStatusId(status);
        userStatus.ifPresent(user::setUserStatus);
        return user;
    }

}
