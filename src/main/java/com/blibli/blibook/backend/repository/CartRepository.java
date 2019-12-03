package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    List<Cart> findByUser_UserIdAndCartStatus_CartStatusId(Integer userId, Integer cartStatusId);

}
