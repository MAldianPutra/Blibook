package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SigninDTO {

    private String userEmail;
    private String userPassword;

}
