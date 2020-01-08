package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.Response;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.repository.UserRepository;
import com.blibli.blibook.backend.repository.UserRoleRepository;
import com.blibli.blibook.backend.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

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

    public Optional<UserStatus> findUserStatusId(Integer userStatusId){
        return userStatusRepository.findById(userStatusId);
    }

    public Optional<UserRole> findUserRoleId(Integer userRoleId){
        return userRoleRepository.findById(userRoleId);
    }

    public User findFirstByUserId(Integer userId){
        return userRepository.findFirstByUserId(userId);
    }

    public User save (User user){
        return userRepository.save(user);
    }

    public Response register (User user) {
        ArrayList<User> objUser = new ArrayList<>();
        Response response;

        try {
            userRepository.save(user);
            objUser.add(userRepository.findFirstByUserId(user.getUserId()));

            if (objUser.get(0) != null) {
                response = new Response(200, "Success", objUser);
            }
            else {
                response = new Response(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new Response(500, ex.getCause().getMessage(), null);
        }

        return response;
    }


    public Response updateUser (User user) {
        ArrayList<User> objUser = new ArrayList<>();
        Response response;

        try {
            User temp = userRepository.findFirstByUserId(user.getUserId());
            temp.setUserEmail(user.getUserEmail());
            temp.setUserName(user.getUserName());
            temp.setUserBirthdate(user.getUserBirthdate());
            temp.setUserHandphone(user.getUserHandphone());
            temp.setUserGender(user.getUserGender());

            userRepository.save(temp);
            objUser.add(userRepository.findFirstByUserId(temp.getUserId()));

            if (objUser.get(0) != null) {
                response = new Response(200, "Success", objUser);
            }
            else {
                response = new Response(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new Response(500, ex.getCause().getMessage(), null);
        }

        return response;
    }


    public Response login (String email, String password) {
        ArrayList<User> objUser = new ArrayList<>();
        Response response;

        objUser.add(userRepository.findFirstByUserEmailAndUserPassword(email, password));

        if (objUser.get(0) != null) {
            response = new Response(200, "Success Login", objUser);
        } else {
            response = new Response(404, "User Not Found!", null);
        }

        return response;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public long deleteByUserId(Integer userId){
        return userRepository.deleteByUserId(userId);
    }

}
