package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findFirstByOrderId(Integer orderId);

    List<Order> findByUser_UserIdAndAndOrderStatus_OrderStatusId(Integer userId, Integer orderStatusId);

}
