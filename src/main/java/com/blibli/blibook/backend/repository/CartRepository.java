package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
