package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(ApiPath.CART_BY_USER_ID)
    public ResponseDTO findCartByUser(@RequestParam Integer userId){
        Integer cartStatusId = 1;
        return cartService.findByUserAndCartStatus(userId, cartStatusId);
    }

    @GetMapping(ApiPath.WISHLIST_BY_USER_ID)
    public ResponseDTO findWishlistByUser(@RequestParam Integer userId){
        Integer cartStatusId = 2;
        return cartService.findByUserAndCartStatus(userId, cartStatusId);
    }

    @GetMapping(ApiPath.ALL_CARTS)
    public List<Cart> findAll(){
        return cartService.findAll();
    }

    @PostMapping(ApiPath.ADD_PRODUCT_TO_CART)
    public ResponseDTO addCart(@RequestParam Integer userId,
                        @RequestParam Integer productId){
        // cartStatusId 1 = CART
        String cartStatusName = "CART";
        return cartService.addCartOrWishlist(userId, productId, cartStatusName);
    }

    @PostMapping(ApiPath.ADD_PRODUCT_TO_WISHLIST)
    public ResponseDTO addWishlist(@RequestParam Integer userId,
                            @RequestParam Integer productId){
        // cartStatusId 2 = WISHLIST
        String cartStatusName = "WISHLIST";
        return cartService.addCartOrWishlist(userId, productId, cartStatusName);
    }


    @DeleteMapping(ApiPath.WISHLIST_CART_DELETE_BY_ID)
    public ResponseDTO deleteWishlistCartUser(@RequestParam Integer cartId){
        return cartService.deleteWishlistCartUser(cartId);
    }

}
