package com.blibli.blibook.backend.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DetailOrderPayload {

    private Integer productId;
    private String productName;
    private String productAuthor;
    private String productLanguage;
    private Integer productPrice;
    private String productPhotoLink;

}
