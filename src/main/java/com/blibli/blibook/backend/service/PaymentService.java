package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.Payment;
import com.blibli.blibook.backend.repository.OrderRepository;
import com.blibli.blibook.backend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    public Optional<Order> findOrderId(Integer orderId){
        return orderRepository.findById(orderId);
    }

    public List<Payment> findAll(){
        return paymentRepository.findAll();
    }

    public Payment save(Payment payment){
        return paymentRepository.save(payment);
    }

}
