package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.payload.DetailOrderPayload;
import com.blibli.blibook.backend.payload.LibraryPayload;
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

    public List<LibraryPayload> findUserLibrary(Integer userId, Integer orderStatusId){
        List<Order> orderList = orderRepository.findByUser_UserIdAndOrderStatus_OrderStatusId(userId, orderStatusId);
        List<LibraryPayload> libraryPayloads = new ArrayList<>();
        for (Order order : orderList){
            Product product = productRepository.findFirstByProductId(order.getProduct().getProductId());
            libraryPayloads.add(new LibraryPayload(
                    product.getProductId(),
                    product.getProductPhotoLink()));
        }
        return libraryPayloads;
    }

    public List<DetailOrderPayload> findOrderList(List<Order> orderList){
        List<DetailOrderPayload> detailOrderPayloads = new ArrayList<>();
        for (Order order : orderList){
            Product product = productRepository.findFirstByProductId(order.getProduct().getProductId());
            detailOrderPayloads.add(new DetailOrderPayload(
                    product.getProductId(),
                    product.getProductName(),
                    product.getProductAuthor(),
                    product.getProductLanguage(),
                    product.getProductPrice(),
                    product.getProductPhotoLink()
            ));
        }
        return detailOrderPayloads;
    }

}
