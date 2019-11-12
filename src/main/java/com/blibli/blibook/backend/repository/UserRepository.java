package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByUserId(Integer userId);

    @Transactional
    long deleteByUserId(Integer userId);

//    Optional<User> findByUsername(String userName);
//
//    Optional<User> findByEmail(String userEmail);
//
//    Optional<User>  findByUsernameOrEmail(String userName, String userEmail);
//
//    List<User> findById (List<Integer> userIds);
//
//    Boolean existByUsername(String userName);
//
//    Boolean existsByEmail(String userEmail);

}
