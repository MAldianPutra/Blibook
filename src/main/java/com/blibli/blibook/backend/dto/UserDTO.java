package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

    private Integer userId;
    private String userName;
    private String userEmail;
    private String userBirthdate;
    private String userGender;
    private String userHandphone;
    private String userRole;
    private String userStatus;

}
