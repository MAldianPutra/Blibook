package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
