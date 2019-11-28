package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
