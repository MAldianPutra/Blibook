package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.dto.*;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ObjectMapperServiceImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserStatusRepository userStatusRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    OrderRepository orderRepository;

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
        UserRole userRole = userRoleRepository.findFirstByUserRoleId(user.getUserRole().getUserRoleId());
        UserStatus userStatus = userStatusRepository.findFirstByUserStatusId(user.getUserStatus().getUserStatusId());
        return new UserDTO(
                user.getUserId(),
                user.getUserName(),
                user.getUserEmail(),
                user.getUserBirthdate(),
                user.getUserGender(),
                user.getUserHandphone(),
                userRole.getUserRoleName(),
                userStatus.getUserStatusName()
        );
    }

    public OrderShopDTO mapToOrderShopDTO(Order order){
        return new OrderShopDTO(order.getOrderId(),
                mapToUserDTO(userRepository.findFirstByUserId(order.getUser().getUserId())),
                mapToProductDetail(productRepository.findFirstByProductId(order.getProduct().getProductId())));
    }

    public ShopDTO mapToShopDTO(Shop shop){
        User user = userRepository.findFirstByUserId(shop.getUser().getUserId());
        return new ShopDTO(shop.getShopId(), shop.getShopName(), shop.getShopAddress(), shop.getShopCity(), shop.getShopProvince(), user.getUserName());
    }

}
