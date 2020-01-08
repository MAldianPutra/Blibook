package com.blibli.blibook.backend.dto;

import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class OrderShopDTO {
    public Integer orderId;
    public User user;
    public Product product;
}
