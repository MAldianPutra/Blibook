package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShopDTO {
    Integer shopId;
    String shopName;
    String shopAddress;
    String shopCity;
    String shopProvince;
    Integer userId;
    String userName;
}
