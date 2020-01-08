package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor

public class ResponseDTO {
    public Integer status;
    public String message;
    public ArrayList data;
}
