package com.blibli.blibook.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopOrderPayload {

    private Integer orderId;

    private String userName;
    private String userHandphone;

    private String productName;
    private Integer productPrice;

}
