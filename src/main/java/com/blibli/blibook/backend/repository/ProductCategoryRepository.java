package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.model.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

    Optional<ProductCategory> findByProductCategoryName(String productCategoryName);

    ProductCategory findFirstByProductCategoryName(String productCategoryName);

    ProductCategory findFirstByProductCategoryId(Integer id);
}
