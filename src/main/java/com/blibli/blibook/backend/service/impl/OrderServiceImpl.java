package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.payload.DetailOrderPayload;
import com.blibli.blibook.backend.payload.LibraryPayload;
import com.blibli.blibook.backend.repository.OrderRepository;
import com.blibli.blibook.backend.repository.ProductRepository;
import com.blibli.blibook.backend.repository.ShopRepository;
import com.blibli.blibook.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ProductRepository productRepository;

    public List<LibraryPayload> findUserLibrary(Integer userId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByUser_UserIdAndOrderStatus_OrderStatusId(userId, orderStatusId);
        List<LibraryPayload> libraryPayloads = new ArrayList<>();
        for (Order order : orderList){
            Product productRepo = productRepository.findFirstByProductId(order.getProduct().getProductId());
            libraryPayloads.add(new LibraryPayload(
                    productRepo.getProductName(),
                    productRepo.getProductAuthor(),
                    productRepo.getProductDescription()));
        }
        return libraryPayloads;
    }

    public List<DetailOrderPayload> findOrderList(List<Order> orderList){
        List<DetailOrderPayload> detailOrderPayloads = new ArrayList<>();
        for (Order order : orderList){
            User userRepo = userRepository.findFirstByUserId(order.getUser().getUserId());
            Shop shopRepo = shopRepository.findFirstByShopId(order.getShop().getShopId());
            Product productRepo = productRepository.findFirstByProductId(order.getProduct().getProductId());
            detailOrderPayloads.add(new DetailOrderPayload(
                    userRepo.getUserName(),
                    userRepo.getUserEmail(),
                    shopRepo.getShopName(),
                    productRepo.getProductName(),
                    productRepo.getProductPrice(),
                    productRepo.getProductAuthor()));
        }
        return detailOrderPayloads;
    }

}
