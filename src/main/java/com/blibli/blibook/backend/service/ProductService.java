package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping(ApiPath.PRODUCT_BY_PRODUCT_ID)
    public Product findByProductId(Integer productId){
        return productRepository.findFirstByProductId(productId);
    }

    @GetMapping(ApiPath.PRODUCT_BY_PRODUCT_CATEGORY)
    public List<Product> findByProductCategory(Integer productCategoryId){
        return productRepository.findByProductCategory_ProductCategoryId(productCategoryId);
    }
}
