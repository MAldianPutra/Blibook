package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Integer> {
    UserRole findFirstByUserRoleId(Integer id);
}
