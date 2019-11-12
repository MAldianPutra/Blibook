package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.ApiPath;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @GetMapping(ApiPath.SHOP_BY_SHOP_ID)
    public Shop findByShopId(Integer shopId){
        return shopRepository.findFirstByShopId(shopId);
    }

    @PostMapping(ApiPath.SHOP)
    public Shop save(Shop shop){
        return shopRepository.save(shop);
    }
}
