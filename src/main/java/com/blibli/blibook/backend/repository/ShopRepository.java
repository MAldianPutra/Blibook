package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

    Shop findFirstByShopId(Integer shopId);

    @Transactional
    long deleteByShopId(Integer shopId);

}
