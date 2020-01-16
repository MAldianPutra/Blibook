package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Cart;
import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.OrderStatus;
import com.blibli.blibook.backend.model.entity.Payment;
import com.blibli.blibook.backend.repository.OrderRepository;
import com.blibli.blibook.backend.repository.PaymentRepository;
import com.blibli.blibook.backend.service.impl.OrderServiceImpl;
import com.blibli.blibook.backend.service.impl.PaymentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PaymentServiceImpl paymentServiceImpl;

    public Optional<Order> findOrderId(Integer orderId){
        return orderRepository.findById(orderId);
    }

    public ResponseDTO findAll(){
        try{
            List<Payment> payments = paymentRepository.findAll();
            ArrayList<Payment> data = new ArrayList<>(payments);
            return new ResponseDTO(200, "Success.", data);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }
    }

    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }

    public ResponseDTO paymentProduct(Integer orderId) {
        return paymentServiceImpl.paymentProduct(orderId);
    }
}
