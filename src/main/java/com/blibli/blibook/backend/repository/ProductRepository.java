package com.blibli.blibook.backend.repository;

import com.blibli.blibook.backend.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Product findFirstByProductId(Integer productId);

    List<Product> findByProductCategory_ProductCategoryName(String productCategoryName);

    List<Product> findByShop_ShopId(Integer shopId);

    List<Product> findByProductNameContaining(String searchKey);

    List<Product> findByProductPriceLessThanEqual(Integer priceDemand);


}
