package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.dto.ShopDTO;
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

    public ResponseDTO shopRegister (Shop shop, Integer userId) {
        ArrayList<Shop> objShop = new ArrayList<>();
        ArrayList<User> temp = new ArrayList<>();
        ResponseDTO response;

        User user = userRepository.findFirstByUserId(userId);
        temp.add(user);

        if (temp.get(0) == null) {
            response = new ResponseDTO(404, "User Not Found", null);
        } else {
            shop.setUser(user);

            try {
                shopRepository.save(shop);
                objShop.add(shopRepository.findFirstByShopId(shop.getShopId()));

                if (objShop.get(0) != null) {
                    response = new ResponseDTO(200, "Success", objShop);
                }
                else {
                    response = new ResponseDTO(400, "Failed!", null);
                }
            } catch (DataAccessException ex) {
                response = new ResponseDTO(500, ex.getCause().getMessage(), null);
            }
        }

        return response;
    }


    public ResponseDTO updateShop (Shop shop) {
        ArrayList<Shop> objShop = new ArrayList<>();
        ResponseDTO response;

        try {
            Shop temp = shopRepository.findFirstByShopId(shop.getShopId());
            temp.setShopName(shop.getShopName());
            temp.setShopAddress(shop.getShopAddress());
            temp.setShopProvince(shop.getShopProvince());
            temp.setShopCity(shop.getShopCity());

            shopRepository.save(temp);
            objShop.add(shopRepository.findFirstByShopId(temp.getShopId()));

            if (objShop.get(0) != null) {
                response = new ResponseDTO(200, "Success", objShop);
            }
            else {
                response = new ResponseDTO(400, "Failed!", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }


    public ResponseDTO findShopByUserId (Integer userId) {
        ArrayList<ShopDTO> objShop = new ArrayList<>();
        ArrayList<Shop> temp = new ArrayList<>();
        ResponseDTO response;

        try {
            Shop shop = shopRepository.findFirstByUserUserId(userId);
            temp.add(shop);

            if (temp.get(0) == null) {
                response = new ResponseDTO(404, "Shop Not Found!", null);
            } else {
                User user = userRepository.findFirstByUserId(userId);
                objShop.add(new ShopDTO(
                        shop.getShopId(),
                        shop.getShopName(),
                        shop.getShopAddress(),
                        shop.getShopCity(),
                        shop.getShopProvince(),
                        user.getUserName()
                ));

                if (objShop.get(0) != null) {
                    response = new ResponseDTO(200, "Success", objShop);
                }
                else {
                    response = new ResponseDTO(400, "Shop Not Found!", null);
                }
            }
        }
        catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }


    public ResponseDTO getAllShops() {
        ArrayList<ShopDTO> objShop = new ArrayList<>();
        ResponseDTO response;
        List<Shop> shops = shopRepository.findAll();

        for (Shop shop : shops) {
            User user = userRepository.findFirstByUserId(shop.getUser().getUserId());

            objShop.add(new ShopDTO(
                    shop.getShopId(),
                    shop.getShopName(),
                    shop.getShopAddress(),
                    shop.getShopCity(),
                    shop.getShopProvince(),
                    user.getUserName()
            ));
        }

        if (objShop.get(0) != null) {
            response = new ResponseDTO(200, "Success", objShop);
        } else {
            response = new ResponseDTO(400, "Failed!", null);
        }

        return response;
    }

    public ResponseDTO deleteByShopId(Integer shopId) {
        ResponseDTO response;
        ArrayList<Shop> objShop = new ArrayList<>();

        try {
            Shop shop = shopRepository.findFirstByUserUserId(shopId);
            Long success = shopRepository.deleteByShopId(shopId);

            if (success > 0) {
                objShop.add(shop);
                response = new ResponseDTO(200, "Success", objShop);
            } else {
                response = new ResponseDTO(404, "Shop Not Found!", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }

    public Integer countShopByUserId(Integer userId){
        return shopRepository.countShopByUser_UserId(userId);
    }

//    public List<Shop> findAll(){
//        return shopRepository.findAll();
//    }

}
