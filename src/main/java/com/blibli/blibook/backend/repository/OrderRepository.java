package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    Order findFirstByOrderId(Integer orderId);

    Order findByUser_UserIdAndProduct_ProductId(Integer userId, Integer productId);

    List<Order> findByUser_UserIdAndOrderStatus_OrderStatusId(Integer userId, Integer orderStatusId);

    List<Order> findByShop_ShopIdAndOrderStatus_OrderStatusId(Integer shopId, Integer orderStatusId);

    Boolean existsOrderByUser_UserIdAndProduct_ProductId(Integer userId, Integer productId);

}
