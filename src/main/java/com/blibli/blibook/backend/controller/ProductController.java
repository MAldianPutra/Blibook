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

    @GetMapping(ApiPath.PRODUCT)
    public Product findByProductId(@RequestParam Integer id){
        return productService.findFirstByProductId(id);
    }

    @GetMapping(ApiPath.PRODUCT_BY_PRODUCT_CATEGORY_ID)
    public List<Product> findByProductCategory(@RequestParam Integer categoryId){
        return productService.findByProductCategory(categoryId);
    }

    @GetMapping(ApiPath.PRODUCT_BY_SHOP_ID)
    public List<Product> findByShop(@RequestParam Integer shopId){
        return productService.findByShop(shopId);
    }

    // Not Yet Completed
    @PostMapping(ApiPath.PRODUCT)
    public Product save(@RequestBody Product product){
        return productService.save(product);
    }

}
