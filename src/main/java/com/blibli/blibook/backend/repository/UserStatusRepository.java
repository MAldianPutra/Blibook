package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {

    UserStatus findFirstByUserStatusId(Integer userStatusId);

    UserStatus findFirstByUserStatusName(String userStatusName);
}
