package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(ApiPath.USER_BY_USER_ID)
    public User findByUserId(@PathVariable Integer userId){
        return userService.findByUserId(userId);
    }

    @PostMapping(ApiPath.USER)
    public User save(@RequestBody User user){
        return userService.save(user);
    }

    @DeleteMapping(ApiPath.USER_BY_USER_ID)
    public boolean deleteByUserId(@PathVariable Integer userId){
        return userService.deleteByUserId(userId) > 0;
    }
}
