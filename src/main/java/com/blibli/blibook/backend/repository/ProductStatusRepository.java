package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductStatusRepository extends JpaRepository<ProductStatus, Integer> {
}
