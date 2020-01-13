package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.model.entity.ProductCategory;
import com.blibli.blibook.backend.model.entity.ProductStatus;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.service.ProductService;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FileUploadServiceImpl fileUploadService;

    @GetMapping(ApiPath.PRODUCT)
    public ResponseDTO findByProductId(@RequestParam Integer id){
        return productService.findProductDetailById(id);
    }

    @GetMapping(ApiPath.PRODUCT_BY_PRODUCT_CATEGORY_NAME)
    public ResponseDTO findByProductCategory(@RequestParam ("name") String productCategoryName){
        return productService.findProductReviewByCategoryName(productCategoryName);
    }

    @GetMapping(ApiPath.PRODUCT_BY_SHOP_ID)
    public ResponseDTO findByShop(@RequestParam Integer shopId){
        return productService.findProductReviewByShopId(shopId);
    }

    @GetMapping(ApiPath.PRODUCT_SEARCH_BY_NAME)
    public ResponseDTO findByProductNameLike(@RequestParam ("key") String searchKey){
        return productService.findProductReviewBySearchKey(searchKey);
    }

    @GetMapping(ApiPath.PRODUCT_SEARCH_BY_PRICE_LESS_THAN)
    public ResponseDTO findByProductPriceLessThan(@RequestParam ("lessThan") Integer priceDemand){
        return productService.findProductReviewByPriceLessThan(priceDemand);
    }

    @GetMapping(ApiPath.PRODUCT_SEARCH_BY_COUNTRY)
    public ResponseDTO findProductByCountry(@RequestParam ("country") String productCountry){
        return productService.findProductByCountry(productCountry);
    }

    @GetMapping(ApiPath.ALL_PRODUCTS)
    public ResponseDTO findAll(@RequestParam ("page") Integer page) {
        return productService.findAll(page);
    }

    @PostMapping(ApiPath.PRODUCT)
    public Product createProduct(@RequestParam ("shop") Integer shopId,
                                 @RequestParam ("category") String productCategoryName,
                                 @RequestParam ("item") MultipartFile item,
                                 @RequestParam ("photo") MultipartFile photo,
                                 @RequestParam ("product") String productString) throws IOException {
            ObjectMapper mapper = new ObjectMapper();
            Product product = mapper.readValue(productString, Product.class);
            Optional<ProductCategory> productCategory = productService.findProductCategoryByProductCategoryName(productCategoryName);
            productCategory.ifPresent(product::setProductCategory);
            Optional<ProductStatus> productStatus = productService.findProductStatusByProductStatusName("AVAILABLE");
            productStatus.ifPresent(product::setProductStatus);
            Optional<Shop> shop = productService.findShopByShopId(shopId);
            shop.ifPresent(product::setShop);

            product = productService.populateSKU(product);
            if(fileUploadService.validatePhoto(photo) && fileUploadService.validateItem(item)){
                    productService.save(product);
                    productService.uploadProductItem(product.getProductId(), item);
                    productService.uploadProductPhoto(product.getProductId(), photo);
                    return productService.findProductById(product.getProductId());
            }
            return null;
    }

    @PostMapping(ApiPath.POPULATE_SKU)
    public ResponseDTO populateAllSKU(){
        return productService.populateAllSKU();
    }

    @DeleteMapping(ApiPath.PRODUCT_DELETE_BY_ID)
    public ResponseDTO deleteBlockProductByID(@RequestParam ("id") Integer productId) {
        return productService.deleteBlockProductByID(productId);
    }


    @PutMapping(ApiPath.PRODUCT_UPDATE_BY_ID)
    public Product updateProductByID(@RequestParam ("product") String productString,
                                      @RequestParam ("item") MultipartFile item,
                                      @RequestParam ("photo") MultipartFile photo,
                                      @RequestParam ("category") String productCategoryName) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productString, Product.class);

        productService.uploadProductItem(product.getProductId(), item);
        productService.uploadProductPhoto(product.getProductId(), photo);

        return productService.updateProductByID(product, productCategoryName);
    }

}
