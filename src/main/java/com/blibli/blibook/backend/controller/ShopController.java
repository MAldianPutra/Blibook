package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@Api
@RestController
@RequestMapping(produces = MediaType.APPLICATION_JSON_VALUE)
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping(ApiPath.SHOP)
    public ResponseDTO findByShopId(@RequestParam Integer id){
        return shopService.findByShopId(id);
    }

    @PostMapping(ApiPath.SHOP_REGISTER)
    public ResponseDTO shopRegister(@RequestParam ("shop") String shop,
                                    @RequestParam ("userId") Integer userId) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Shop objShop = mapper.readValue(shop, Shop.class);

        return shopService.shopRegister(objShop, userId);
    }

    @PutMapping(ApiPath.SHOP_UPDATE)
    public ResponseDTO shopUpdate(@RequestParam ("shop") String shop) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Shop objShop = mapper.readValue(shop, Shop.class);

        return shopService.updateShop(objShop);
    }


    @GetMapping(ApiPath.SHOP_BY_USER_ID)
    public ResponseDTO findShopByUserId(@RequestParam Integer userId) {
        return shopService.findShopByUserId(userId);
    }

    @GetMapping(ApiPath.ALL_SHOP)
    public ResponseDTO findAllWithPaging(@RequestParam ("page") Integer page) {
        return shopService.findAllShopWithPaging(page);
    }

    @GetMapping(ApiPath.SHOP_ALL)
    public ResponseDTO findAll(){
        return shopService.findAll();
    }

}
