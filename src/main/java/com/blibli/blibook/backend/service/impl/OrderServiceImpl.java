package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.dto.ProductPhotoDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.repository.OrderRepository;
import com.blibli.blibook.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ObjectMapperServiceImpl objectMapperService;

    public List<ProductPhotoDTO> findUserLibrary(Integer userId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByUser_UserIdAndOrderStatus_OrderStatusId(userId, orderStatusId);
        List<ProductPhotoDTO> productPhotoDTOList = new ArrayList<>();
        for (Order order : orderList){
            Product product = productRepository.findFirstByProductId(order.getProduct().getProductId());
            productPhotoDTOList.add(objectMapperService.mapToProductPhoto(product));
        }
        return productPhotoDTOList;
    }

    public List<ProductReviewDTO> findOrderList(List<Order> orderList){
        List<ProductReviewDTO> productReviewDTOList = new ArrayList<>();
        for (Order order : orderList){
            Product product = productRepository.findFirstByProductId(order.getProduct().getProductId());
            objectMapperService.mapToProductReview(product);
        }
        return productReviewDTOList;
    }

}
