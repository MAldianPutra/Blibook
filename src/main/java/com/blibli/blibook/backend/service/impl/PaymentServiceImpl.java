package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Cart;
import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.OrderStatus;
import com.blibli.blibook.backend.model.entity.Payment;
import com.blibli.blibook.backend.repository.PaymentRepository;
import com.blibli.blibook.backend.service.OrderService;
import com.blibli.blibook.backend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class PaymentServiceImpl {

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaymentService paymentService;

    @Autowired
    OrderService orderService;

    public ResponseDTO paymentProduct(Integer orderId){
        try{
            // orderStatusId 2 = WAITING_CONFIRMATION
            Integer orderStatusId = 2;
            Optional<OrderStatus> orderStatus = orderService.findOptionalOrderStatusByOrderStatusId(orderStatusId);
            Order updateOrder = orderService.findFirstByOrderId(orderId);
            updateOrder.setOrderStatus(orderStatus.get());

            // Update orderStatusId in Order table
            orderService.save(updateOrder);

            // Delete Cart
            if(orderService.existsCartByUserIdAndProductId(updateOrder.getUser().getUserId(), updateOrder.getProduct().getProductId())){
                Cart cart = orderService.findCartByUserIdAndProductId(updateOrder.getUser().getUserId(), updateOrder.getProduct().getProductId());
                orderService.deleteCart(cart.getCartId());
            }

            // Save new Payment to DB
            Optional<Order> order = paymentService.findOrderId(orderId);
            Payment payment = new Payment();
            payment.setDatePayment(LocalDateTime.now());
            payment.setOrder(order.get());
            paymentRepository.save(payment);

            // Put into ResponseDTO
            ArrayList<Payment> data = new ArrayList<>();
            data.add(payment);
            return new ResponseDTO(200, "Success", data);

        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }
    }

}
