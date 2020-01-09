package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.model.entity.ProductCategory;
import com.blibli.blibook.backend.model.entity.ProductStatus;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductPhotoDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.repository.ProductCategoryRepository;
import com.blibli.blibook.backend.repository.ProductRepository;
import com.blibli.blibook.backend.repository.ProductStatusRepository;
import com.blibli.blibook.backend.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductCategoryRepository productCategoryRepository;

    @Autowired
    ProductStatusRepository productStatusRepository;

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    ObjectMapperServiceImpl objectMapperService;

    public Optional<ProductCategory> findProductCategoryByProductCategoryName(String productCategoryName){
        return productCategoryRepository.findByProductCategoryName(productCategoryName);
    }

    public Optional<ProductStatus> findProductStatusByProductStatusName(String productStatusName){
        return productStatusRepository.findByProductStatusName(productStatusName);
    }

    public Optional<Shop> findShopByShopId(Integer shopId){
        return shopRepository.findById(shopId);
    }

    public List<ProductPhotoDTO> findProductPhotoList(List<Product> products){
        List<ProductPhotoDTO> productPhotoDTOList = new ArrayList<>();
        for(Product product : products){
            productPhotoDTOList.add(new ProductPhotoDTO(
                    product.getProductId(),
                    product.getProductPhotoLink()
            ));
        }
        return productPhotoDTOList;
    }

    public List<ProductReviewDTO> findProductByCountry(List<Product> products) {
        List<ProductReviewDTO> productReviewDTOList = new ArrayList<>();
        for(Product product : products){
            objectMapperService.mapToProductReview(product);
        }
        return productReviewDTOList;
    }

    public List<ProductReviewDTO> findProductReviewList(List<Product> products){
        List<ProductReviewDTO> productReviewDTOList = new ArrayList<>();
        for(Product product : products){
            objectMapperService.mapToProductReview(product);
        }
        return productReviewDTOList;
    }

    public ProductDetailDTO findProductDetail(Integer productId){
        Product product = productRepository.findFirstByProductId(productId);
        return objectMapperService.mapToProductDetail(product);
    }

}
