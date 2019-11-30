package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping(ApiPath.ADD_PRODUCT_TO_CART)
    public Cart addToCart(@PathVariable Integer userId,
                          @PathVariable Integer productId,
                          @PathVariable Integer shopId){
        // cartStatusId 1 = CART
        Integer cartStatusId = 1;
        return constructCart(cartStatusId, userId, productId, shopId);
    }

    @PostMapping(ApiPath.ADD_PRODUCT_TO_WISHLIST)
    public Cart addToWishlist(@PathVariable Integer userId,
                          @PathVariable Integer productId,
                          @PathVariable Integer shopId){
        // cartStatusId 2 = WISHLIST
        Integer cartStatusId = 2;
        return constructCart(cartStatusId,userId, productId, shopId);
    }

    private Cart constructCart(@PathVariable Integer cartStatusId,
                               @PathVariable Integer userId,
                               @PathVariable Integer productId,
                               @PathVariable Integer shopId) {
        Optional<CartStatus> cartStatus = cartService.findCartStatusId(cartStatusId);
        Optional<User> user = cartService.findUserId(userId);
        Optional<Product> product = cartService.findProductId(productId);
        Optional<Shop> shop = cartService.findShopId(shopId);
        Cart newCart = new Cart();
        newCart.setCartStatus(cartStatus.get());
        newCart.setUser(user.get());
        newCart.setProduct(product.get());
        newCart.setShop(shop.get());
        return cartService.save(newCart);
    }
}
