package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findFirstByProductId(Integer productId);

    List<Product> findByProductCountryAndProductStatus_ProductStatusName(
            String productCountry, String productStatusName);

    List<Product> findByProductCategory_ProductCategoryNameAndProductStatus_ProductStatusName(
            String productCategoryName, String productStatusName);

    List<Product> findByShop_ShopIdAndProductStatus_ProductStatusName(
            Integer shopId, String productStatusName);

    List<Product> findByProductNameContainingAndProductStatus_ProductStatusName(
            String searchKey, String productStatusName);

    List<Product> findByProductPriceLessThanEqualAndProductStatus_ProductStatusName(
            Integer priceDemand, String productStatusName);

    Boolean existsByProductSku(String sku);

    @Transactional
    Long deleteByProductId(Integer productId);
}
