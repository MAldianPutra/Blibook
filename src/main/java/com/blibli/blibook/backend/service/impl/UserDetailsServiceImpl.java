package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.model.entity.UserRole;
import com.blibli.blibook.backend.repository.UserRepository;
import com.blibli.blibook.backend.repository.UserRoleRepository;
import com.blibli.blibook.backend.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(userEmail).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email : " + userEmail));
        UserRole userRole = userRoleRepository.findFirstByUserRoleId(user.getUserRole().getUserRoleId());
        return UserDetails.create(user, userRole);
    };

    @Transactional
    public org.springframework.security.core.userdetails.UserDetails loadUserById(Integer id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found with id : " + id)
        );
        UserRole userRole = userRoleRepository.findFirstByUserRoleId(user.getUserRole().getUserRoleId());
        return UserDetails.create(user, userRole);
    }

}
