package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
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

    @GetMapping(ApiPath.USER)
    public User findByUserId(@RequestParam Integer id){
        return userService.findByUserId(id);
    }

    //Testing. Delete Later
    @PostMapping(ApiPath.USER_SIGNUP)
    public User save(@RequestParam Integer roleId,
                     @RequestParam Integer statusId,
                     @RequestBody User user){
        Optional<UserRole> userRole = userService.findUserRoleId(roleId);
        userRole.ifPresent(user::setUserRole);
        Optional<UserStatus> userStatus = userService.findUserStatusId(statusId);
        userStatus.ifPresent(user::setUserStatus);
        System.out.println("role : " + roleId.toString() + "status : " + statusId.toString());
        return userService.save(user);
    }

    @DeleteMapping(ApiPath.USER_DELETE)
    public boolean deleteByUserId(@RequestParam Integer id){
        return userService.deleteByUserId(id) > 0;
    }
}
