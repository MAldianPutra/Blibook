package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartStatusRepository extends JpaRepository<CartStatus, Integer> {

    CartStatus findFirstByCartStatusName(String cartStatusName);

}
