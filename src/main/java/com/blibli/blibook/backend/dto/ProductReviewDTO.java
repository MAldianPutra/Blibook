package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductReviewDTO {

    private Integer productId;
    private String productName;
    private String productAuthor;
    private String productLanguage;
    private String productDescription;
    private Integer productPrice;
    private String productPhotoLink;

}
