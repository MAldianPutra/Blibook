package com.blibli.blibook.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor

public class Response {
    public Integer status;
    public String message;
    public ArrayList data;
}
