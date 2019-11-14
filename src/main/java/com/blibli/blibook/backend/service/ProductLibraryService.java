package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.ProductLibrary;
import com.blibli.blibook.backend.repository.ProductLibraryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class ProductLibraryService {

    @Autowired
    private ProductLibraryRepository productLibraryRepository;

    @GetMapping(ApiPath.PRODUCT_LIBRARY_BY_PRODUCT_LIBRARY_ID)
    public ProductLibrary findByProductLibraryId(Integer productLibraryId){
        return productLibraryRepository.findFirstByProductLibraryId(productLibraryId);
    }

    @GetMapping(ApiPath.PRODUCT_LIBRARY_BY_USER_ID)
    public List<ProductLibrary> findByUser(Integer userId){
        return productLibraryRepository.findByUser_UserId(userId);
    }

    @PostMapping(ApiPath.PRODUCT_LIBRARY)
    public ProductLibrary save(ProductLibrary productLibrary){
        return productLibraryRepository.save(productLibrary);
    }

}
