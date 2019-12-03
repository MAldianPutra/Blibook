package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    public Shop findByShopId(Integer shopId){
        return shopRepository.findFirstByShopId(shopId);
    }

    public Shop save(Shop shop){
        return shopRepository.save(shop);
    }

}
