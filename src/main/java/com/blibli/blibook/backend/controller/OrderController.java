package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
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
    public List<ProductReviewDTO> shopOrderWaitingConfirm(@RequestParam ("id") Integer shopId){
        // orderStatusId 2 = WAITING_CONFIRMATION
        Integer orderStatusId = 2;
        return orderService.findByShopIdAndOrderStatusId(shopId, orderStatusId);
    }

    @GetMapping(ApiPath.LIBRARY_BY_USER_ID)
    public List<ProductPhotoDTO> userLibrary(@RequestParam ("id") Integer userId) {
        // orderStatusId 3 = COMPLETED
        Integer orderStatusId = 3;
        return orderService.findUserLibrary(userId, orderStatusId);
    }

    @PostMapping(ApiPath.ORDER_INITIATE)
    public String initiateOrder(@RequestParam Integer userId,
                               @RequestParam Integer productId){
        // orderStatusId 1 = NOT_PAID
        Integer orderStatusId = 1;
        if(orderService.findOrderExists(userId, productId)){
            return "Order is already Exists";
        }
        else
        {
            constructOrder(orderStatusId, userId, productId);
            return "Order Initiated";
        }
    }

    @PutMapping(ApiPath.ORDER_CONFIRMATION)
    public Order confirmOrder(@RequestParam Integer id){
        // orderStatusId 3 = COMPLETED
        Integer orderStatusId = 3;
        Optional<OrderStatus> orderStatus = orderService.findOrderStatusId(orderStatusId);
        Order updateOrder = orderService.findFirstByOrderId(id);
        updateOrder.setOrderStatus(orderStatus.get());
        if(orderService.existsCartByUserIdAndProductId(updateOrder.getUser().getUserId(), updateOrder.getProduct().getProductId())){
            Cart cart = orderService.findCartByUserIdAndProductId(updateOrder.getUser().getUserId(), updateOrder.getProduct().getProductId());
            orderService.deleteCart(cart.getCartId());
        }
        return orderService.save(updateOrder);
    }

    private void constructOrder(@RequestParam Integer statusId,
                                @RequestParam Integer userId,
                                @RequestParam Integer productId){
        Optional<OrderStatus> orderStatus = orderService.findOrderStatusId(statusId);
        Optional<User> user = orderService.findUserId(userId);
        Optional<Product> product = orderService.findProductId(productId);
        Order newOrder = new Order();
        orderStatus.ifPresent(newOrder::setOrderStatus);
        user.ifPresent(newOrder::setUser);
        product.ifPresent(newOrder::setProduct);
        newOrder.setShop(product.get().getShop());
        orderService.save(newOrder);
    }

}
