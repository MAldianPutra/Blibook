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
    private UserStatusRepository userStatusRepository;

    @Autowired
    private ProductRepository productRepository;
    public Optional<Product> findProductId(Integer productId){
        return productRepository.findById(productId);
    }

    @Autowired
    private ShopRepository shopRepository;

    @Autowired
    ObjectMapperServiceImpl objectMapperService;

    public ResponseDTO findByUserAndCartStatus(Integer userId, Integer cartStatusId){
        List<Cart> carts = cartRepository.findByUser_UserIdAndCartStatus_CartStatusId(userId, cartStatusId);
        ArrayList<CartDTO> cartDTOList = new ArrayList<>();
        ResponseDTO response;

        for (Cart cart : carts) {
            Product product = productRepository.findFirstByProductId(cart.getProduct().getProductId());
            cartDTOList.add(new CartDTO(cart.getCartId(), objectMapperService.mapToProductDetailDTO(product)));
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
                cartDTOList.add(new CartDTO(cartId, objectMapperService.mapToProductDetailDTO(product)));
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

    public Cart constructCart(Integer userId, Integer productId, String cartStatusName) {
        CartStatus cartStatus = cartStatusRepository.findFirstByCartStatusName(cartStatusName);
        Optional<User> user = findUserId(userId);
        Optional<Product> product = findProductId(productId);
        Cart newCart = new Cart();
        newCart.setCartStatus(cartStatus);
        user.ifPresent(newCart::setUser);
        product.ifPresent(newCart::setProduct);
        newCart.setShop(product.get().getShop());
        save(newCart);
        return newCart;
    }


    public ResponseDTO addCartOrWishlist(Integer userId, Integer productId, String cartStatusName) {
        try{
            if(validateUser(userId, productId)){
                ArrayList<CartDTO> data = new ArrayList<>();
                if(cartRepository.existsCartByUser_UserIdAndProduct_ProductId(userId, productId)){
                    Cart cart = cartRepository.findByUser_UserIdAndProduct_ProductId(userId, productId);
                    CartStatus cartStatus = cartStatusRepository.findFirstByCartStatusId(cart.getCartStatus().getCartStatusId());
                    if(cartStatus.getCartStatusName().equals("CART") &&
                            !cartStatus.getCartStatusName().equals(cartStatusName)){
                        CartStatus updatedStatus = cartStatusRepository.findFirstByCartStatusName("WISHLIST");
                        cart.setCartStatus(updatedStatus);
                        save(cart);
                        CartDTO cartDTO = objectMapperService.mapToCartDTO(cart);
                        data.add(cartDTO);
                        return new ResponseDTO(200, "Changed to Wishlist!", data);
                    }else if(cartStatus.getCartStatusName().equals("WISHLIST") &&
                            !cartStatus.getCartStatusName().equals(cartStatusName)){
                        CartStatus updatedStatus = cartStatusRepository.findFirstByCartStatusName("CART");
                        cart.setCartStatus(updatedStatus);
                        save(cart);
                        CartDTO cartDTO = objectMapperService.mapToCartDTO(cart);
                        data.add(cartDTO);
                        return new ResponseDTO(200, "Changed to Cart!", data);
                    }else{
                        return new ResponseDTO(400, "Failed! Cart or Wishlist Already Created.", null);
                    }
                }else{
                    Cart cart = constructCart(userId, productId, cartStatusName);
                    CartDTO cartDTO = objectMapperService.mapToCartDTO(cart);
                    data.add(cartDTO);
                    return new ResponseDTO(200, "Cart or Wishlist created!", data);
                }
            }else{
                return new ResponseDTO(400, "Sorry, you are blocked or you attempted to buy your own product!", null);
            }
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getCause().getMessage(), null);
        }
    }

    private Boolean validateUser(Integer userId, Integer productId){
        User user = userRepository.findFirstByUserId(userId);
        UserStatus userStatus = userStatusRepository.findFirstByUserStatusId(user.getUserStatus().getUserStatusId());
        Product product = productRepository.findFirstByProductId(productId);
        Shop shop = shopRepository.findFirstByShopId(product.getShop().getShopId());
        if(!userStatus.getUserStatusName().equals("BLOCKED")){
            User user2 = userRepository.findFirstByUserId(shop.getUser().getUserId());
            return !user2.getUserId().equals(user.getUserId());
        }else {
            return false;
        }
    }

}
