package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Cart;
import com.blibli.blibook.backend.model.entity.Order;
import com.blibli.blibook.backend.model.entity.Payment;
import com.blibli.blibook.backend.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    ShopService shopService;

    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;

    @Autowired
    PaymentService paymentService;

    @GetMapping(ApiPath.ALL_USERS)
    public ResponseDTO findAllUserWithPaging(@RequestParam("page") Integer page) {
        return userService.findAllUserWithPaging(page);
    }

    @GetMapping(ApiPath.ALL_SHOP)
    public ResponseDTO findAllShopWithPaging(@RequestParam ("page") Integer page) {
        return shopService.findAllShopWithPaging(page);
    }

    @GetMapping(ApiPath.ALL_PRODUCTS)
    public ResponseDTO findAllProductWithPaging(@RequestParam ("page") Integer page) {
        return productService.findAllProductWithPaging(page);
    }

    @GetMapping(ApiPath.ALL_CARTS)
    public ResponseDTO findAllCart(){
        return cartService.findAll();
    }

    @GetMapping(ApiPath.ALL_ORDER)
    public ResponseDTO findAllOrder(){
        return orderService.findAll();
    }

    @GetMapping(ApiPath.ALL_PAYMENT)
    public ResponseDTO findAllPayment(){
        return paymentService.findAll();
    }

    @PostMapping(ApiPath.POPULATE_SKU)
    public ResponseDTO populateAllSKU(){
        return productService.populateAllSKU();
    }
}
