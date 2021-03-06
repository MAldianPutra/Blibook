package com.blibli.blibook.backend.dto;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDetailDTO {

    private Integer productId;
    private String productName;
    private String productAuthor;
    private String productIsbn;
    private String productSku;
    private String productCountry;
    private Integer productPrice;
    private String productDescription;
    private Integer productLength;
    private Integer productReleaseYear;
    private String productLanguage;
    private String productVariant;
    private String productPhotoLink;
    private Integer productCategory;
    private String productCategoryName;

    private Integer shopId;
    private String shopName;
    private String shopAddress;
    private String shopCity;
    private String shopProvince;


}
