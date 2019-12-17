package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.blibli.blibook.backend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
            UserDTO userDTO = new UserDTO(user.getUserName(), user.getUserEmail());
            return userDTO;
    }

    //Testing. Delete Later
    @PostMapping(ApiPath.USER_SIGNUP)
    public User saveUser(@RequestBody User user){
        Optional<UserRole> userRole = userService.findUserRoleId(2);
        userRole.ifPresent(user::setUserRole);
        Optional<UserStatus> userStatus = userService.findUserStatusId(1);
        userStatus.ifPresent(user::setUserStatus);
        return userService.save(user);
    }

    @PutMapping(ApiPath.USER_UPDATE)
    public User updateUser(@RequestParam ("id") Integer userId,
                           @RequestBody User user){
        User updatedUser = userService.findFirstByUserId(userId);
        updatedUser.setUserName(user.getUserName());
        updatedUser.setUserEmail(user.getUserEmail());
        updatedUser.setUserBirthdate(user.getUserBirthdate());
        updatedUser.setUserGender(user.getUserGender());
        updatedUser.setUserPassword(user.getUserPassword());
        updatedUser.setUserPasswordConfirmation(user.getUserPasswordConfirmation());
        return userService.save(updatedUser);

    }

    @DeleteMapping(ApiPath.USER_DELETE)
    public boolean deleteByUserId(@RequestParam ("id") Integer userId){
        return userService.deleteByUserId(userId) > 0;
    }

}
