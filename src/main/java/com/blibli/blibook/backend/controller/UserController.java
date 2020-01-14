package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.blibli.blibook.backend.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FileUploadServiceImpl fileUploadServiceImpl;

    @GetMapping(ApiPath.USER)
    public UserDTO findByUserId(@RequestParam ("id") Integer userId) {
        User user = userService.findFirstByUserId(userId);
        UserDTO userDTO = null;

        UserRole userRole = userService.findFirstByUserRoleId(user.getUserRole().getUserRoleId());
        UserStatus userStatus = userService.findFirstByUserStatusId(user.getUserStatus().getUserStatusId());

        if (user != null) {
            userDTO = new UserDTO(user.getUserId(), user.getUserName(), user.getUserEmail(), user.getUserBirthdate(),
                    user.getUserGender(), user.getUserHandphone(), userRole.getUserRoleName(), userStatus.getUserStatusName());
        } else {
            userDTO = new UserDTO(0, "-", "-", "-",
                    "-", "-", "-", "-");
        }

        return userDTO;
    }


    @PostMapping(ApiPath.USER_REGISTER)
    public ResponseDTO register(@RequestParam ("user") String userData) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        User user = mapper.readValue(userData, User.class);

        Optional<UserRole> userRole = userService.findUserRoleId(2);
        userRole.ifPresent(user::setUserRole);
        Optional<UserStatus> userStatus = userService.findUserStatusId(1);
        userStatus.ifPresent(user::setUserStatus);


        return userService.register(user);
    }

    @PutMapping(ApiPath.USER_UPDATE)
    public ResponseDTO updateUser(@RequestParam ("user") String user,
                                  @RequestParam ("role") Integer role,
                                  @RequestParam ("status") Integer status) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User objUser = mapper.readValue(user, User.class);

        Optional<UserRole> userRole = userService.findUserRoleId(role);
        userRole.ifPresent(objUser::setUserRole);
        Optional<UserStatus> userStatus = userService.findUserStatusId(status);
        userStatus.ifPresent(objUser::setUserStatus);

        return userService.updateUser(objUser);
    }

    @PostMapping(ApiPath.USER_LOGIN)
    public ResponseDTO login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.login(email, password);
    }

    @DeleteMapping(ApiPath.USER_DELETE)
    public ResponseDTO deleteByUserId(@RequestParam ("id") Integer userId){
        return userService.deleteByUserID(userId);
    }

    @GetMapping(ApiPath.ALL_USERS)
    public ResponseDTO findAllWithPaging(@RequestParam ("page") Integer page) {
        return userService.findAllWithPaging(page);
    }

    @GetMapping(ApiPath.USER_ALL)
    public ResponseDTO findAll(){
        return userService.findAll();
    }

    @PutMapping(ApiPath.USER_BLOCK)
    public ResponseDTO blockUser(@RequestParam("id") Integer userId){
        return userService.blockByUserId(userId);
    }

}
