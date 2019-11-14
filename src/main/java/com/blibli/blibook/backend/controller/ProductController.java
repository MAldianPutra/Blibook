package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.service.ProductService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(ApiPath.PRODUCT_BY_PRODUCT_ID)
    public Product findByProductId(@PathVariable Integer productId){
        return productService.findByProductId(productId);
    }

    @GetMapping(ApiPath.PRODUCT_BY_PRODUCT_CATEGORY)
    public List<Product> findByProductCategory(@PathVariable Integer productCategoryId){
        return productService.findByProductCategory(productCategoryId);
    }

    @PostMapping(ApiPath.PRODUCT)
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

}
