package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartStatusRepository cartStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ShopRepository shopRepository;

    public Optional<CartStatus> findCartStatusId(Integer cartStatusId){
        return cartStatusRepository.findById(cartStatusId);
    }

    public Optional<User> findUserId(Integer userId){
        return userRepository.findById(userId);
    }

    public Optional<Product> findProductId(Integer productId){
        return productRepository.findById(productId);
    }

    public Optional<Shop> findShopId(Integer shopId){
        return shopRepository.findById(shopId);
    }

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

}
