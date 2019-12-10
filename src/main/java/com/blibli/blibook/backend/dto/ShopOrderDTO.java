package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopOrderDTO {

    private Integer orderId;

    private String userName;
    private String userHandphone;

    private String productName;
    private Integer productPrice;

}
