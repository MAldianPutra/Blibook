package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.service.ProductService;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @GetMapping(ApiPath.PRODUCT)
    public ProductDetailDTO findByProductId(@RequestParam Integer id){
        return productService.findProductDetailById(id);
    }

    @GetMapping(ApiPath.PRODUCT_BY_PRODUCT_CATEGORY_ID)
    public List<ProductReviewDTO> findByProductCategory(@RequestParam ("name") String productCategoryName){
        return productService.findProductReviewByCategoryName(productCategoryName);
    }

    @GetMapping(ApiPath.PRODUCT_BY_SHOP_ID)
    public List<ProductReviewDTO> findByShop(@RequestParam Integer shopId){
        return productService.findProductReviewByShopId(shopId);
    }

    @GetMapping(ApiPath.PRODUCT_SEARCH_BY_NAME)
    public List<ProductReviewDTO> findByProductNameLike(@RequestParam ("key") String searchKey){
        return productService.findProductReviewBySearchKey(searchKey);
    }

    @GetMapping(ApiPath.PRODUCT_SEARCH_BY_PRICE_LESS_THAN)
    public List<ProductReviewDTO> findByProductPriceLessThan(@RequestParam ("lessThan") Integer priceDemand){
        return productService.findProductReviewByPriceLessThan(priceDemand);
    }

    @GetMapping(ApiPath.ALL_PRODUCTS)
    public List<Product> findAll(){
        return productService.findAll();
    }

    // Kaitkan dengan cek login
    @PostMapping(ApiPath.PRODUCT)
    public Product save(@RequestParam ("photo") MultipartFile photo,
                        @RequestParam ("item") MultipartFile item,
                        @RequestBody Product product) throws IOException {
        productService.save(product);
        fileUploadService.uploadProductPhoto(product.getProductId(), photo);
        fileUploadService.uploadProductItem(product.getProductId(), item);
        return product;
    }

}
