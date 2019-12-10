package com.blibli.blibook.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPayload {

    private Integer productId;
    private String productName;
    private Integer productPrice;
    private String productDescription;
    private String productPhotoLink;


}
