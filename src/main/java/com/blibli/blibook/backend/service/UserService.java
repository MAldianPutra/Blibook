package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.repository.UserRepository;
import com.blibli.blibook.backend.repository.UserRoleRepository;
import com.blibli.blibook.backend.repository.UserStatusRepository;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserStatusRepository userStatusRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ObjectMapperServiceImpl objectMapperService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public Optional<UserStatus> findUserStatusId(Integer userStatusId) {
        return userStatusRepository.findById(userStatusId);
    }

    public Optional<UserRole> findUserRoleId(Integer userRoleId) {
        return userRoleRepository.findById(userRoleId);
    }

    public User findFirstByUserId(Integer userId) {
        return userRepository.findFirstByUserId(userId);
    }

    public UserRole findFirstByUserRoleId(Integer id) {
        return userRoleRepository.findFirstByUserRoleId(id);
    }

    public UserStatus findFirstByUserStatusId(Integer id) {
        return userStatusRepository.findFirstByUserStatusId(id);
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public ResponseDTO register(User user) {
        ArrayList<User> objUser = new ArrayList<>();
        ResponseDTO response;

        try {
            user.setUserPassword(passwordEncoder().encode(user.getUserPassword()));
            userRepository.save(user);
            objUser.add(userRepository.findFirstByUserId(user.getUserId()));

            if (objUser.get(0) != null) {
                response = new ResponseDTO(200, "Success", objUser);
            } else {
                response = new ResponseDTO(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }


    public ResponseDTO updateUser(User user) {
        ArrayList<User> objUser = new ArrayList<>();
        ResponseDTO response;
        try {
            User temp = userRepository.findFirstByUserId(user.getUserId());
            temp.setUserEmail(user.getUserEmail());
            temp.setUserName(user.getUserName());
            temp.setUserBirthdate(user.getUserBirthdate());
            temp.setUserHandphone(user.getUserHandphone());
            temp.setUserGender(user.getUserGender());
            temp.setUserRole(user.getUserRole());
            temp.setUserStatus(user.getUserStatus());

            userRepository.save(temp);
            objUser.add(userRepository.findFirstByUserId(temp.getUserId()));

            if (objUser.get(0) != null) {
                response = new ResponseDTO(200, "Success", objUser);
            } else {
                response = new ResponseDTO(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }


    public ResponseDTO login(String email, String password) {
        ArrayList<UserDTO> objUser = new ArrayList<>();
        try {
            User user = userRepository.findFirstByUserEmail(email);
            UserStatus userStatus = userStatusRepository.findFirstByUserStatusId(user.getUserStatus().getUserStatusId());
            if (user != null && passwordEncoder().matches(password, user.getUserPassword())
                    && userStatus.getUserStatusName().equals("AVAILABLE")) {
                objUser.add(objectMapperService.mapToUserDTO(user));
                return new ResponseDTO(200, "Success Login", objUser);
            } else {
                return new ResponseDTO(404, "User Not Found or Blocked!", null);
            }
        } catch (DataAccessException ex) {
            return new ResponseDTO(500, ex.getMessage(), null);
        }
    }

    public ResponseDTO getAllUser(Integer page) {
        ArrayList<UserDTO> objUser = new ArrayList<>();
        Page<User> userPage = userRepository.findAll(PageRequest.of(page, 20, Sort.by("userName").ascending()));

        for (User user : userPage) {
            objUser.add(objectMapperService.mapToUserDTO(user));
        }

        if (objUser.get(0) != null) {
            ArrayList<ArrayList> data = new ArrayList<>();
            ArrayList<String> countData = new ArrayList<>();
            countData.add("Data count : " + userRepository.count());
            data.add(countData);
            data.add(objUser);
            return new ResponseDTO(200, "Success", objUser);
        } else {
            return new ResponseDTO(404, "User Is Empty!", null);
        }

    }


    public ResponseDTO deleteByUserID(Integer userId) {
        ResponseDTO response;
        ArrayList<User> objUser = new ArrayList<>();

        try {
            User user = userRepository.findFirstByUserId(userId);
            Long success = userRepository.deleteByUserId(userId);

            if (success > 0) {
                objUser.add(user);
                response = new ResponseDTO(200, "Success", objUser);
            } else {
                response = new ResponseDTO(400, "Failed", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }
}