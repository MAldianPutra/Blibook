package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductPhotoDTO {

    private Integer productId;
    private String productPhotoLink;

}
