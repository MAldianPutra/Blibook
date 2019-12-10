package com.blibli.blibook.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailProductPayload {

    private Integer productId;
    private String productName;
    private String productAuthor;
    private String productIsbn;
    private String productSku;
    private String productCountry;
    private Integer productPrice;
    private String productDescription;
    private String productLength;
    private Integer productReleaseYear;
    private String productLanguage;
    private String productVariant;
    private String productPhotoLink;

    private String shopId;
    private String shopName;
    private String shopAddress;
    private String shopCity;
    private String shopProvince;


}
