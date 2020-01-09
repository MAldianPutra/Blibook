package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtDTO {

    private String accessToken;
    private String tokenType = "Bearer";

    public JwtDTO(String accessToken) {
        this.accessToken = accessToken;
    }

}
