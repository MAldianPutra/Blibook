package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.payload.DetailOrderPayload;
import com.blibli.blibook.backend.payload.LibraryPayload;
import com.blibli.blibook.backend.repository.*;
import com.blibli.blibook.backend.service.impl.OrderServiceImpl;
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

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    public Order findFirstByOrderId(Integer orderId){
        return orderRepository.findFirstByOrderId(orderId);
    }

    public List<DetailOrderPayload> findByUserIdAndOrderStatusId(Integer userId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByUser_UserIdAndOrderStatus_OrderStatusId(userId, orderStatusId);
        return orderServiceImpl.findOrderList(orderList);
    }

    public List<DetailOrderPayload> findByShopIdAndOrderStatusId(Integer shopId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByShop_ShopIdAndAndOrderStatus_OrderStatusId(shopId, orderStatusId);
        return orderServiceImpl.findOrderList(orderList);
    }

    public List<LibraryPayload> findUserLibrary(Integer userId, Integer orderStatusId){
        return orderServiceImpl.findUserLibrary(userId, orderStatusId);
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

}
