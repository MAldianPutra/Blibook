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
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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

        if (user != null) {
            userDTO = new UserDTO(user.getUserName(), user.getUserEmail(), user.getUserBirthdate(),
                    user.getUserGender(), user.getUserHandphone());
        } else {
            userDTO = new UserDTO("-", "-", "-",
                    "-", "-");
        }

        return userDTO;
    }

    @GetMapping(ApiPath.ALL_USERS)
    public List<User> findAll(){
        return userService.findAll();
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
    public ResponseDTO updateUser(@RequestParam ("user") String user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User objUser = mapper.readValue(user, User.class);

        return userService.updateUser(objUser);
    }

    @PostMapping(ApiPath.USER_LOGIN)
    public ResponseDTO login(@RequestParam("email") String email, @RequestParam("password") String password) {
        return userService.login(email, password);
    }

    @DeleteMapping(ApiPath.USER_DELETE)
    public boolean deleteByUserId(@RequestParam ("id") Integer userId){
        return userService.deleteByUserId(userId) > 0;
    }

}
