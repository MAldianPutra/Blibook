package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product findFirstByProductId(Integer productId){
        return productRepository.findFirstByProductId(productId);
    }

    public List<Product> findByProductCategory(Integer productCategoryId){
        return productRepository.findByProductCategory_ProductCategoryId(productCategoryId);
    }

    public List<Product> findByShop(Integer shopId){
        return productRepository.findByShop_ShopId(shopId);
    }

    public List<Product> findByProductNameLike(String searchKey){
        return productRepository.findByProductNameContaining(searchKey);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

}
