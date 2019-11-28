package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderStatusRepository extends JpaRepository<OrderStatus, Integer> {
}
