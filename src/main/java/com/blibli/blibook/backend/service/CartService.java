package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartStatusRepository cartStatusRepository;
    public Optional<CartStatus> findCartStatusId(Integer cartStatusId){
        return cartStatusRepository.findById(cartStatusId);
    }

    @Autowired
    private UserRepository userRepository;
    public Optional<User> findUserId(Integer userId){
        return userRepository.findById(userId);
    }

    @Autowired
    private ProductRepository productRepository;
    public Optional<Product> findProductId(Integer productId){
        return productRepository.findById(productId);
    }


    @Autowired
    private ShopRepository shopRepository;
    public Optional<Shop> findShopId(Integer shopId){
        return shopRepository.findById(shopId);
    }

    public List<Cart> findByUserAndCartStatus(Integer userId, Integer cartStatusId){
        return cartRepository.findByUser_UserIdAndCartStatus_CartStatusId(userId, cartStatusId);
    }

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

}
