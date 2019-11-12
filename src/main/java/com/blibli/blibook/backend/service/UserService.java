package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(ApiPath.USER_BY_USER_ID)
    public User findByUserId(Integer userId){
        return userRepository.findFirstByUserId(userId);
    }

    @PostMapping(ApiPath.USER)
    public User save (User user){
//        user.setUserPassword(bCryptPasswordEncoder.encode);
        return userRepository.save(user);
    }

    @DeleteMapping(ApiPath.USER_BY_USER_ID)
    public long deleteByUserId(Integer userId){
        return userRepository.deleteByUserId(userId);
    }
}
