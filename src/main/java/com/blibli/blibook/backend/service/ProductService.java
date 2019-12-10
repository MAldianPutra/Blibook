package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.repository.ProductRepository;
import com.blibli.blibook.backend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    public Product findProductById(Integer productId){
        return productRepository.findFirstByProductId(productId);
    }

    public ProductDetailDTO findProductDetailById(Integer productId){
        return productServiceImpl.findProductDetail(productId);
    }

    public List<ProductReviewDTO> findProductReviewByCategoryName(String productCategoryName){
        List<Product> products = productRepository.findByProductCategory_ProductCategoryName(productCategoryName);
        return productServiceImpl.findProductReviewList(products);
    }

    public List<ProductReviewDTO> findProductReviewByShopId(Integer shopId){
        List<Product> products = productRepository.findByShop_ShopId(shopId);
        return productServiceImpl.findProductReviewList(products);
    }

    public List<ProductReviewDTO> findProductReviewBySearchKey(String searchKey){
        List<Product> products = productRepository.findByProductNameContaining(searchKey);
        return productServiceImpl.findProductReviewList(products);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }



}
