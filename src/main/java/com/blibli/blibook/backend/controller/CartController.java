package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.service.CartService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping(ApiPath.CART_BY_USER_ID)
    public List<Cart> findCartByUser(@RequestParam Integer userId){
        // cartStatusId 1 = CART
        Integer cartStatusId = 1;
        return cartService.findByUserAndCartStatus(userId, cartStatusId);
    }

    @GetMapping(ApiPath.WISHLIST_BY_USER_ID)
    public List<Cart> findWishlistByUser(@RequestParam Integer userId){
        // cartStatusId 2 = WISHLIST
        Integer cartStatusId = 2;
        return cartService.findByUserAndCartStatus(userId, cartStatusId);
    }

    @PostMapping(ApiPath.ADD_PRODUCT_TO_CART)
    public Cart addCart(@RequestParam Integer userId,
                        @RequestParam Integer productId,
                        @RequestParam Integer shopId){
        // cartStatusId 1 = CART
        Integer cartStatusId = 1;
        return constructCart(cartStatusId, userId, productId, shopId);
    }

    @PostMapping(ApiPath.ADD_PRODUCT_TO_WISHLIST)
    public Cart addWishlist(@RequestParam Integer userId,
                            @RequestParam Integer productId,
                            @RequestParam Integer shopId){
        // cartStatusId 2 = WISHLIST
        Integer cartStatusId = 2;
        return constructCart(cartStatusId,userId, productId, shopId);
    }

    private Cart constructCart(@RequestParam Integer cartStatusId,
                               @RequestParam Integer userId,
                               @RequestParam Integer productId,
                               @RequestParam Integer shopId) {
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
