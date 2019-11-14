package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.ProductLibrary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductLibraryRepository extends JpaRepository<ProductLibrary, Integer> {

    ProductLibrary findFirstByProductLibraryId(Integer productLibraryId);

    List<ProductLibrary> findByUser_UserId(Integer userId);
    

}
