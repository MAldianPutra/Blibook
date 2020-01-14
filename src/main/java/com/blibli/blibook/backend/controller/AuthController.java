package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.JwtDTO;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.dto.SigninDTO;
import com.blibli.blibook.backend.jwt.JwtTokenProvider;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.repository.UserRepository;
import com.blibli.blibook.backend.repository.UserRoleRepository;
import com.blibli.blibook.backend.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenProvider tokenProvider;

    @PostMapping(ApiPath.SIGN_IN)
    public ResponseEntity<?> authenticateUser(@RequestBody SigninDTO signinDTO){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signinDTO.getUserEmail(),
                        signinDTO.getUserPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtDTO(jwt));
    }

    @PostMapping(ApiPath.REGISTER)
    public ResponseDTO registerUser(@RequestBody User user){
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        Optional<UserRole> userRole = userService.findUserRoleId(2);
        userRole.ifPresent(user::setUserRole);
        Optional<UserStatus> userStatus = userService.findUserStatusId(1);
        userStatus.ifPresent(user::setUserStatus);
        return userService.register(user);
    }

}
