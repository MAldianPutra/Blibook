package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.service.ShopService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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

    // Not Yet Completed
    @PostMapping(ApiPath.SHOP)
    public Shop save(@RequestBody Shop shop){
        return shopService.save(shop);
    }

}
