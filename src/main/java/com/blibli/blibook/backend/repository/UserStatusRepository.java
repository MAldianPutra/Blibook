package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserStatusRepository extends JpaRepository<UserStatus, Integer> {
}
