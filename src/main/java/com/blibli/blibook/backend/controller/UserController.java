package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
//import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.blibli.blibook.backend.payload.UserPayload;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.blibli.blibook.backend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    public UserPayload findByUserId(@RequestParam ("id") Integer userId){
        User user = userService.findFirstByUserId(userId);
        UserPayload userPayload = new UserPayload(user.getUserName(), user.getUserEmail(), user.getUserPhotoLink());
        return userPayload;
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

    @PutMapping(ApiPath.UPLOAD_USER_PHOTO)
    public UserPayload updatePhoto(@RequestParam ("id") Integer userId,
                                   @RequestParam ("file") MultipartFile multipartFile) throws IOException {
        return fileUploadServiceImpl.uploadUserPhoto(userId, multipartFile);
    }

    @DeleteMapping(ApiPath.USER_DELETE)
    public boolean deleteByUserId(@RequestParam ("id") Integer userId){
        return userService.deleteByUserId(userId) > 0;
    }

}
