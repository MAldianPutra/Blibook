package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.model.entity.UserStatus;
import com.blibli.blibook.backend.repository.UserRepository;
import com.blibli.blibook.backend.repository.UserRoleRepository;
import com.blibli.blibook.backend.repository.UserStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public long deleteByUserId(Integer userId){
        return userRepository.deleteByUserId(userId);
    }

}
