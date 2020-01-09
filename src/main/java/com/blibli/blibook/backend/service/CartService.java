package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.CartDTO;
import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import com.blibli.blibook.backend.service.impl.ObjectMapperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartStatusRepository cartStatusRepository;
    public Optional<CartStatus> findCartStatusId(Integer cartStatusId){
        return cartStatusRepository.findById(cartStatusId);
    }

    @Autowired
    private UserRepository userRepository;
    public Optional<User> findUserId(Integer userId){
        return userRepository.findById(userId);
    }

    @Autowired
    private ProductRepository productRepository;
    public Optional<Product> findProductId(Integer productId){
        return productRepository.findById(productId);
    }


    @Autowired
    ObjectMapperServiceImpl objectMapperService;

    public ResponseDTO findByUserAndCartStatus(Integer userId, Integer cartStatusId){
        List<Cart> carts = cartRepository.findByUser_UserIdAndCartStatus_CartStatusId(userId, cartStatusId);
        ArrayList<CartDTO> cartDTOList = new ArrayList<>();
        ResponseDTO response;

        for (Cart cart : carts) {
            Product product = productRepository.findFirstByProductId(cart.getProduct().getProductId());
            cartDTOList.add(new CartDTO(cart.getCartId(), objectMapperService.mapToProductDetail(product)));
        }

        response = new ResponseDTO(200, "Success", cartDTOList);

        return response;
    }

    public ResponseDTO deleteWishlistCartUser(Integer cartId) {
        ArrayList<CartDTO> cartDTOList = new ArrayList<>();
        ResponseDTO response;

        try {
            Cart cart = cartRepository.findFirstByCartId(cartId);
            Product product = productRepository.findFirstByProductId(cart.getProduct().getProductId());

            Long success = cartRepository.deleteByCartId(cartId);

            if (success > 0) {
                cartDTOList.add(new CartDTO(cartId, objectMapperService.mapToProductDetail(product)));
                response = new ResponseDTO(200, "Sukses", cartDTOList);
            } else {
                response = new ResponseDTO(404, "ID Not Found", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

    public List<Cart> findAll(){
        return cartRepository.findAll();
    }

}
