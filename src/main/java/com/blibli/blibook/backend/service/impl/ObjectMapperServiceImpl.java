package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductPhotoDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.model.entity.ProductCategory;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperServiceImpl {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    public ProductDetailDTO mapToProductDetail(Product product){
        ProductCategory productCategory = productCategoryRepository.findFirstByProductCategoryId(product.getProductCategory().getProductCategoryId());
        Shop shop = shopRepository.findFirstByShopId(product.getShop().getShopId());

        return new ProductDetailDTO(
                product.getProductId(),
                product.getProductName(),
                product.getProductAuthor(),
                product.getProductIsbn(),
                product.getProductSku(),
                product.getProductCountry(),
                product.getProductPrice(),
                product.getProductDescription(),
                product.getProductLength(),
                product.getProductReleaseYear(),
                product.getProductLanguage(),
                product.getProductVariant(),
                product.getProductPhotoLink(),
                productCategory.getProductCategoryId(),
                productCategory.getProductCategoryName(),
                shop.getShopId(),
                shop.getShopName(),
                shop.getShopAddress(),
                shop.getShopCity(),
                shop.getShopProvince()
        );
    }

    public ProductReviewDTO mapToProductReview(Product product){
        return new ProductReviewDTO(
                product.getProductId(),
                product.getProductName(),
                product.getProductAuthor(),
                product.getProductLanguage(),
                product.getProductDescription(),
                product.getProductPrice(),
                product.getProductPhotoLink()
        );
    }

    public ProductPhotoDTO mapToProductPhoto(Product product){
        return new ProductPhotoDTO(
                product.getProductId(),
                product.getProductPhotoLink()
        );
    }

    public UserDTO mapToUserDTO(User user){
        return new UserDTO(
                user.getUserName(),
                user.getUserEmail(),
                user.getUserBirthdate(),
                user.getUserGender(),
                user.getUserHandphone()
        );
    }

}
