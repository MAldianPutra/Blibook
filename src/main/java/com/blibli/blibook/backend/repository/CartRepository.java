package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    Cart findByUser_UserIdAndProduct_ProductId(Integer userId, Integer productId);

    Cart findFirstByCartId(Integer cartId);;

    List<Cart> findByUser_UserIdAndCartStatus_CartStatusId(Integer userId, Integer cartStatusId);

    Boolean existsCartByUser_UserIdAndProduct_ProductId(Integer userId, Integer productId);

    @Transactional
    long deleteByCartId(Integer cartId);

}
