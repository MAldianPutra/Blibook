package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.dto.ProductPhotoDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(ApiPath.ORDER_NOT_PAID_BY_USER_ID)
    public List<ProductReviewDTO> userOrderNotPaid(@RequestParam ("id") Integer userId){
        // orderStatusId 3 = NOT_PAID
        Integer orderStatusId = 1;
        return orderService.findByUserIdAndOrderStatusId(userId, orderStatusId);
    }

    @GetMapping(ApiPath.ORDER_NOT_PAID_BY_SHOP_ID)
    public List<ProductReviewDTO> shopOrderNotPaid(@RequestParam ("id") Integer shopId){
        // orderStatusId 3 = NOT_PAID
        Integer orderStatusId = 1;
        return orderService.findByShopIdAndOrderStatusId(shopId, orderStatusId);
    }

    @GetMapping(ApiPath.ORDER_WAITING_CONFIRM_BY_USER_ID)
    public List<ProductReviewDTO> userOrderWaitingConfirm(@RequestParam ("id") Integer userId){
        // orderStatusId 2 = WAITING_CONFIRMATION
        Integer orderStatusId = 2;
        return orderService.findByUserIdAndOrderStatusId(userId, orderStatusId);
    }

    @GetMapping(ApiPath.ORDER_WAITING_CONFIRM_BY_SHOP_ID)
    public ResponseDTO findOrderWaitingShopId(@RequestParam ("id") Integer shopId){
        Integer orderStatusId = 2;
        return orderService.findOrderWaitingShopId(shopId, orderStatusId);
    }

    @GetMapping(ApiPath.LIBRARY_BY_USER_ID)
    public List<ProductPhotoDTO> userLibrary(@RequestParam ("id") Integer userId) {
        // orderStatusId 3 = COMPLETED
        Integer orderStatusId = 3;
        return orderService.findUserLibrary(userId, orderStatusId);
    }

    @GetMapping(ApiPath.ALL_ORDER)
    public List<Order> findAll(){
        return orderService.findAll();
    }

    @GetMapping(ApiPath.ORDERED_PRODUCT_BY_ORDER_ID)
    public ResponseDTO orderedProductById(@RequestParam ("id") Integer orderId){
        return orderService.orderedProductById(orderId);
    }

    @PostMapping(ApiPath.ORDER_INITIATE)
    public ResponseDTO initiateOrder(@RequestParam Integer userId,
                                     @RequestParam Integer productId){
        return orderService.initiateOrder(userId, productId);
    }

    @PutMapping(ApiPath.ORDER_CONFIRMATION)
    public Order confirmOrder(@RequestParam Integer id){
        // orderStatusId 3 = COMPLETED
        Integer orderStatusId = 3;
        Optional<OrderStatus> orderStatus = orderService.findOptionalOrderStatusByOrderStatusId(orderStatusId);
        Order updateOrder = orderService.findFirstByOrderId(id);
        updateOrder.setOrderStatus(orderStatus.get());
        return orderService.save(updateOrder);
    }

}
