package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.dto.ProductPhotoDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
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
    private ProductRepository productRepository;
    public Optional<Product> findProductId(Integer productId){
        return productRepository.findById(productId);
    }

    @Autowired
    private CartRepository cartRepository;
    public Boolean existsCartByUserIdAndProductId(Integer userId, Integer productId){
        return cartRepository.existsCartByUser_UserIdAndProduct_ProductId(userId, productId);
    }
    public Cart findCartByUserIdAndProductId(Integer userId, Integer productId){
        return cartRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
    }
    public long deleteCart(Integer cartId){
        return deleteCart(cartId);
    }

    @Autowired
    private OrderServiceImpl orderServiceImpl;

    public Order findFirstByOrderId(Integer orderId){
        return orderRepository.findFirstByOrderId(orderId);
    }

    public List<ProductReviewDTO> findByUserIdAndOrderStatusId(Integer userId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByUser_UserIdAndOrderStatus_OrderStatusId(userId, orderStatusId);
        return orderServiceImpl.findOrderList(orderList);
    }

    public List<ProductReviewDTO> findByShopIdAndOrderStatusId(Integer shopId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByShop_ShopIdAndOrderStatus_OrderStatusId(shopId, orderStatusId);
        return orderServiceImpl.findOrderList(orderList);
    }

    public List<ProductPhotoDTO> findUserLibrary(Integer userId, Integer orderStatusId){
        return orderServiceImpl.findUserLibrary(userId, orderStatusId);
    }

    public boolean findOrderExists(Integer userId, Integer productId){
        return orderRepository.existsOrderByUser_UserIdAndProduct_ProductId(userId, productId);
    }

    public Order save(Order order){
        return orderRepository.save(order);
    }

}
