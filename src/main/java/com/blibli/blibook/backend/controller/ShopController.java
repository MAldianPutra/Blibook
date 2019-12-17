package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping(ApiPath.SHOP)
    public Shop findByShopId(@RequestParam Integer id){
        return shopService.findByShopId(id);
    }

    @PostMapping(ApiPath.SHOP)
    public Shop createShop(@RequestParam Integer userId,
                           @RequestBody Shop shop){
        Optional<User> user = shopService.findUserId(userId);
        if(shopService.countShopByUserId(userId) > 0){
            return shopService.findByUserId(userId);
        }else{
            user.ifPresent(shop::setUser);
            return shopService.save(shop);
        }
    }

    @PutMapping(ApiPath.SHOP_UPDATE)
    public Shop updateShop(@RequestParam ("id") Integer shopId,
                           @RequestBody Shop shop){
        Shop updatedShop = shopService.findByShopId(shopId);
        updatedShop.setShopName(shop.getShopName());
        updatedShop.setShopAddress(shop.getShopAddress());
        updatedShop.setShopCity(shop.getShopCity());
        updatedShop.setShopProvince(shop.getShopProvince());
        return shopService.save(updatedShop);
    }

}
