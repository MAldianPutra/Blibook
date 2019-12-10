package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.OrderStatus;
import com.blibli.blibook.backend.model.entity.Payment;
import com.blibli.blibook.backend.service.OrderService;
import com.blibli.blibook.backend.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private OrderService orderService;

    @PostMapping(ApiPath.PAYMENT)
    public Payment paymentProduct(@RequestParam Integer orderId){
        // orderStatusId 2 = WAITING_CONFIRMATION
        Integer orderStatusId = 2;
        Optional<OrderStatus> orderStatus = orderService.findOrderStatusId(orderStatusId);
        Order updateOrder = orderService.findFirstByOrderId(orderId);
        updateOrder.setOrderStatus(orderStatus.get());

        // Update orderStatusId in Order table
        orderService.save(updateOrder);

        // Save new Payment to DB
        Optional<Order> order = paymentService.findOrderId(orderId);
        Payment payment = new Payment();
        payment.setDatePayment(LocalDateTime.now());
        payment.setOrder(order.get());
        return paymentService.save(payment);
    }


}
