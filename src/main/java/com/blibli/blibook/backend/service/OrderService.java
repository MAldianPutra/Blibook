package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order findFirstByOrderId(Integer orderId){
        return orderRepository.findFirstByOrderId(orderId);
    }

    public Optional<OrderStatus> findOrderStatusId(Integer orderStatusId){
        return orderStatusRepository.findById(orderStatusId);
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

    public Order save(Order order){
        return orderRepository.save(order);
    }

}
