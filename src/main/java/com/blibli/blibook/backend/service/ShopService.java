package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.dto.ShopDTO;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.model.entity.User;
import com.blibli.blibook.backend.repository.ShopRepository;
import com.blibli.blibook.backend.repository.UserRepository;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private ObjectMapperServiceImpl objectMapperService;

    public ResponseDTO findByShopId(Integer shopId){
        try{
            Shop shop = shopRepository.findFirstByShopId(shopId);
            ArrayList<ShopDTO> data = new ArrayList<>();
            data.add(objectMapperService.mapToShopDTO(shop));
            return new ResponseDTO(200, "Success.", data);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }
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
                objShop.add(objectMapperService.mapToShopDTO(shop));
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


    public ResponseDTO findAllShopWithPaging(Integer page) {
        Page<Shop> shopPage = shopRepository.findAll(PageRequest.of(page, 10, Sort.by("shopName").ascending()));
        ArrayList<ShopDTO> shopDTOS = new ArrayList<>();
        for (Shop shop : shopPage) {
            User user = userRepository.findFirstByUserId(shop.getUser().getUserId());
            shopDTOS.add(objectMapperService.mapToShopDTO(shop));
        }

        if (shopDTOS.get(0) != null) {
            ArrayList<ArrayList> data = new ArrayList<>();
            ArrayList<Long> countData =  new ArrayList<>();
            countData.add(shopRepository.count());
            data.add(countData);
            data.add(shopDTOS);
            return new ResponseDTO(200, "Success", data);
        } else {
            return new ResponseDTO(400, "Failed!", null);
        }

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

    public ResponseDTO findAll() {
        try{
            ArrayList<Shop> data = (ArrayList<Shop>) shopRepository.findAll();
            return new ResponseDTO(200, "Success.", data);
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }

    }

}
