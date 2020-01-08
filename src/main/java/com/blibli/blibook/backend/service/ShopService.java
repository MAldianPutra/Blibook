package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.Response;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.repository.ShopRepository;
import com.blibli.blibook.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public Response shopRegister (Shop shop, Integer userId) {
        ArrayList<Shop> objShop = new ArrayList<>();
        Response response;
        User user = userRepository.findFirstByUserId(userId);
        shop.setUser(user);

        try {
            shopRepository.save(shop);
            objShop.add(shopRepository.findFirstByShopId(shop.getShopId()));

            if (objShop.get(0) != null) {
                response = new Response(200, "Success", objShop);
            }
            else {
                response = new Response(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new Response(500, ex.getCause().getMessage(), null);
        }
        return response;
    }


    public Response updateShop (Shop shop) {
        ArrayList<Shop> objShop = new ArrayList<>();
        Response response;

        try {
            Shop temp = shopRepository.findFirstByShopId(shop.getShopId());
            temp.setShopName(shop.getShopName());
            temp.setShopAddress(shop.getShopAddress());
            temp.setShopProvince(shop.getShopProvince());
            temp.setShopCity(shop.getShopCity());

            shopRepository.save(temp);
            objShop.add(shopRepository.findFirstByShopId(temp.getShopId()));

            if (objShop.get(0) != null) {
                response = new Response(200, "Success", objShop);
            }
            else {
                response = new Response(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new Response(500, ex.getCause().getMessage(), null);
        }

        return response;
    }


    public Response findShopByUserId (Integer userId) {
        ArrayList<Shop> objShop = new ArrayList<>();
        Response response;
        Shop shop = shopRepository.findFirstByUserUserId(userId);
        objShop.add(shop);

        if (objShop.get(0) != null) {
            response = new Response(200, "Success", objShop);
        }
        else {
            response = new Response(400, "Shop Not Found!", null);
        }

        return response;

    public Integer countShopByUserId(Integer userId){
        return shopRepository.countShopByUser_UserId(userId);
    }

    public List<Shop> findAll(){
        return shopRepository.findAll();
    }

}
