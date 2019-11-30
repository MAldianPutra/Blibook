package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping(ApiPath.ORDER_INITIATE)
    public Order initiateOrder(@PathVariable Integer userId,
                               @PathVariable Integer productId,
                               @PathVariable Integer shopId ){
        // orderStatusId 1 = NOT_PAID
        Integer orderStatusId = 1;
        return constructOrder(orderStatusId, userId, productId, shopId);
    }

    @PutMapping(ApiPath.ORDER_CONFIRMATION)
    public Order confirmOrder(@PathVariable Integer orderId){
        // orderStatusId 3 = COMPLETED
        Integer orderStatusId = 3;
        Optional<OrderStatus> orderStatus = orderService.findOrderStatusId(orderStatusId);
        Order updateOrder = orderService.findFirstByOrderId(orderId);
        updateOrder.setOrderStatus(orderStatus.get());
        return orderService.save(updateOrder);
    }

    private Order constructOrder(@PathVariable Integer orderStatusId,
                                 @PathVariable Integer userId,
                                 @PathVariable Integer productId,
                                 @PathVariable Integer shopId){
        Optional<OrderStatus> orderStatus = orderService.findOrderStatusId(orderStatusId);
        Optional<User> user = orderService.findUserId(userId);
        Optional<Product> product = orderService.findProductId(productId);
        Optional<Shop> shop = orderService.findShopId(shopId);
        Order newOrder = new Order();
        newOrder.setOrderStatus(orderStatus.get());
        newOrder.setUser(user.get());
        newOrder.setProduct(product.get());
        newOrder.setShop(shop.get());
        return orderService.save(newOrder);
    }

}
