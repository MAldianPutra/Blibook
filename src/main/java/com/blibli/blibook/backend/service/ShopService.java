package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.repository.ShopRepository;
import com.blibli.blibook.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    private UserRepository userRepository;
    public Optional<User> findUserId(Integer userId){
        return userRepository.findById(userId);
    }

    public Shop findByShopId(Integer shopId){
        return shopRepository.findFirstByShopId(shopId);
    }

    public Shop findByUserId(Integer userId){
        return shopRepository.findFirstByUser_UserId(userId);
    }

    public Integer countShopByUserId(Integer userId){
        return shopRepository.countShopByUser_UserId(userId);
    }

    public List<Shop> findAll(){
        return shopRepository.findAll();
    }

    public Shop save(Shop shop){
        return shopRepository.save(shop);
    }

}
