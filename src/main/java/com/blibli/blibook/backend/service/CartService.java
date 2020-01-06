package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.CartDTO;
import com.blibli.blibook.backend.dto.Response;
import com.blibli.blibook.backend.model.entity.*;
import com.blibli.blibook.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
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

//    public List<Cart> findByUserAndCartStatus(Integer userId, Integer cartStatusId){
//        return cartRepository.findByUser_UserIdAndCartStatus_CartStatusId(userId, cartStatusId);
//    }

    public Response findByUserAndCartStatus(Integer userId, Integer cartStatusId){
        List<Cart> data = cartRepository.findByUser_UserIdAndCartStatus_CartStatusId(userId, cartStatusId);
        ArrayList<CartDTO> cartDTOList = new ArrayList<>();
        Response response;

        for (Cart dataProduct : data) {
            Product product = productRepository.findFirstByProductId(dataProduct.getProduct().getProductId());
            cartDTOList.add(new CartDTO(dataProduct.getCartId(), product));
        }

        response = new Response(200, "Success", cartDTOList);

        return response;
    }

    public Response deleteWishlistCartUser(Integer cartId) {
        ArrayList<CartDTO> cartDTOList = new ArrayList<>();
        Response response;

        try {
            Cart cart = cartRepository.findFirstByCartId(cartId);
            Product product = productRepository.findFirstByProductId(cart.getProduct().getProductId());

            Long success = cartRepository.deleteByCartId(cartId);

            if (success > 0) {
                cartDTOList.add(new CartDTO(cartId, product));
                response = new Response(200, "Sukses", cartDTOList);
            } else {
                response = new Response(404, "ID Not Found", null);
            }
        } catch (DataAccessException ex) {
            response = new Response(500, ex.getCause().getMessage(), null);
        }

        return response;
    }

    public Cart save(Cart cart){
        return cartRepository.save(cart);
    }

}
