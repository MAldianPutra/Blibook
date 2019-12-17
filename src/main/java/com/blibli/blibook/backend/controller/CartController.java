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

    @GetMapping(ApiPath.ALL_CARTS)
    public List<Cart> findAll(){
        return cartService.findAll();
    }

    @PostMapping(ApiPath.ADD_PRODUCT_TO_CART)
    public Cart addCart(@RequestParam Integer userId,
                        @RequestParam Integer productId){
        // cartStatusId 1 = CART
        Integer cartStatusId = 1;
        return constructCart(cartStatusId, userId, productId);
    }

    @PostMapping(ApiPath.ADD_PRODUCT_TO_WISHLIST)
    public Cart addWishlist(@RequestParam Integer userId,
                            @RequestParam Integer productId){
        // cartStatusId 2 = WISHLIST
        Integer cartStatusId = 2;
        return constructCart(cartStatusId,userId, productId);
    }

    @DeleteMapping(ApiPath.CART_DELETE)
    public boolean deleteCart(@RequestParam Integer cartId){
        return cartService.deleteByCartId(cartId) > 0;
    }

    @DeleteMapping(ApiPath.WISHLIST_DELETE)
    public boolean deleteWishlist(@RequestParam Integer cartId){
        return deleteCart(cartId);
    }

    private Cart constructCart(@RequestParam Integer cartStatusId,
                               @RequestParam Integer userId,
                               @RequestParam Integer productId) {
        Optional<CartStatus> cartStatus = cartService.findCartStatusId(cartStatusId);
        Optional<User> user = cartService.findUserId(userId);
        Optional<Product> product = cartService.findProductId(productId);
        Cart newCart = new Cart();
        cartStatus.ifPresent(newCart::setCartStatus);
        user.ifPresent(newCart::setUser);
        product.ifPresent(newCart::setProduct);
        newCart.setShop(product.get().getShop());
        return cartService.save(newCart);
    }

}
