package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.model.entity.ProductCategory;
import com.blibli.blibook.backend.model.entity.ProductStatus;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.service.ProductService;
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
        product.setProductSku(product.getProductId() + "-" + product.getProductName() + "-" + product.getProductVariant());
        productService.save(product);
        productService.uploadProductItem(product.getProductId(), item);
        productService.uploadProductPhoto(product.getProductId(), photo);
        return productService.findProductById(product.getProductId());
    }

    @PutMapping(ApiPath.PRODUCT_UPDATE)
    public Product updateProduct(@RequestParam ("id") Integer productId,
                                 @RequestParam ("item") MultipartFile item,
                                 @RequestParam ("photo") MultipartFile photo,
                                 @RequestParam ("product") String productString) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(productString, Product.class);
        Product updatedProduct = productService.findProductById(productId);
        updatedProduct.setProductName(product.getProductName());
        updatedProduct.setProductAuthor(product.getProductAuthor());
        updatedProduct.setProductCountry(product.getProductCountry());
        updatedProduct.setProductIsbn(product.getProductIsbn());
        updatedProduct.setProductPrice(product.getProductPrice());
        updatedProduct.setProductDescription(product.getProductDescription());
        updatedProduct.setProductLength(product.getProductLength());
        updatedProduct.setProductReleaseYear(product.getProductReleaseYear());
        updatedProduct.setProductSku(product.getProductSku());
        updatedProduct.setProductLanguage(product.getProductVariant());
        productService.save(updatedProduct);
        if(!photo.isEmpty()){
            productService.uploadProductPhoto(product.getProductId(), photo);
        }
        if(!item.isEmpty()){
            productService.uploadProductItem(product.getProductId(), item);
        }
        return productService.findProductById(productId);
    }

    @DeleteMapping(ApiPath.PRODUCT_DELETE)
    public boolean deleteProduct(@RequestParam ("id") Integer productId){
        return productService.deleteByProductId(productId) > 0;
    }

}
