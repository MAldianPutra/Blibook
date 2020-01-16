package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.service.OrderService;
import com.blibli.blibook.backend.service.PaymentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping(ApiPath.PAYMENT)
    public ResponseDTO paymentProduct(@RequestParam Integer orderId){
        return paymentService.paymentProduct(orderId);
    }


}
