package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByUserId(Integer userId);

    User findFirstByUserEmail(String email);

    User findFirstByUserEmailAndUserPassword(String email, String password);

    @Transactional
    long deleteByUserId(Integer userId);
}
