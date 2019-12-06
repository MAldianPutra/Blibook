package com.blibli.blibook.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailOrderPayload {

    private String userName;
    private String userEmail;
    private String shopName;
    private String productName;
    private Float productPrice;
    private String productAuthor;

}
