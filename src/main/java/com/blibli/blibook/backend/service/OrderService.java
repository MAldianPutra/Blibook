package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderStatusRepository orderStatusRepository;
    public Optional<OrderStatus> findOrderStatusId(Integer orderStatusId){
        return orderStatusRepository.findById(orderStatusId);
    }

    @Autowired
    private UserRepository userRepository;
    public Optional<User> findUserId(Integer userId){
        return userRepository.findById(userId);
    }

    @Autowired
    private ShopRepository shopRepository;
    public Optional<Shop> findShopId(Integer shopId){
        return shopRepository.findById(shopId);
    }

    @Autowired
    private ProductRepository productRepository;
    public Optional<Product> findProductId(Integer productId){
        return productRepository.findById(productId);
    }

    public Order findFirstByOrderId(Integer orderId){
        return orderRepository.findFirstByOrderId(orderId);
    }

    public List<Order> findByUserIdAndOrderStatusId(Integer userId, Integer orderStatusId){
        return orderRepository.findByUser_UserIdAndAndOrderStatus_OrderStatusId(userId, orderStatusId);
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

}
