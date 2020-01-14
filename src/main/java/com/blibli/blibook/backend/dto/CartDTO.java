package com.blibli.blibook.backend.dto;

import com.blibli.blibook.backend.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class CartDTO {
    public Integer cartId;
    public ProductDetailDTO productDetailDTO;
}
