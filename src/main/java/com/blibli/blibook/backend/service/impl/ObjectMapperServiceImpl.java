package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductPhotoDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.dto.UserDTO;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.repository.OrderRepository;
import com.blibli.blibook.backend.repository.ProductRepository;
import com.blibli.blibook.backend.repository.ShopRepository;
import com.blibli.blibook.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    OrderRepository orderRepository;

    public ProductDetailDTO mapToProductDetail(Product product, Shop shop){
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
                product.getProductCategory().getProductCategoryId(),
                product.getProductCategory().getProductCategoryName(),
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

    protected UserDTO mapToUserDTO(User user){
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserBirthdate(),
                user.getUserGender(),
                user.getUserHandphone(),
                user.getUserRole().getUserRoleName(),
                user.getUserStatus().getUserStatusName()
        );
    }

}
