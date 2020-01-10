package com.blibli.blibook.backend.service.impl;

import com.blibli.blibook.backend.dto.ResponseDTO;
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
            ProductPhotoDTO productPhotoDTO = objectMapperService.mapToProductPhoto(product);
            productPhotoDTOList.add(productPhotoDTO);
        }
        return productPhotoDTOList;
    }

    public List<ProductReviewDTO> findProductByCountry(List<Product> products) {
        List<ProductReviewDTO> productReviewDTOList = new ArrayList<>();
        for(Product product : products){
            ProductReviewDTO productReview = objectMapperService.mapToProductReview(product);
            productReviewDTOList.add(productReview);
        }
        return productReviewDTOList;
    }

    public List<ProductReviewDTO> findProductReviewList(List<Product> products){
        List<ProductReviewDTO> productReviewDTOList = new ArrayList<>();
        for(Product product : products){
            ProductReviewDTO productReview = objectMapperService.mapToProductReview(product);
            productReviewDTOList.add(productReview);
        }
        return productReviewDTOList;
    }

    public ProductDetailDTO findProductDetail(Integer productId){
        Product product = productRepository.findFirstByProductId(productId);
        return objectMapperService.mapToProductDetail(product);
    }

    public void blockProduct(Product product){
        ProductCategory categoryBlocked = productCategoryRepository.findFirstByProductCategoryName("BLOCKED");
        product.setProductCategory(categoryBlocked);
        productRepository.save(product);
    }

    /**
    SKU = BLI-{SHOP NAME}-{CATEGORY NAME}-{AUTHOR}-{PRODUCT NAME}-{COUNT}

     SHOP NAME = 4 huruf terakhir dari toko
     CATEGORY NAME = 3 huruf pertama dari kategori
     AUTHOR = 4 huruf pertama dari author
     PRODUCT NAME = 4 huruf pertama dari nama produk + 4 huruf terakhir dari nama produk
     COUNT = Jika sudah ada, beri nomor 02

    contoh (nama toko TOKO SAYA, author ANDREA HIRATA, produk LASKAR PELANGI)
    BLI-SAYA-NOV-ANDR-LASKANGI-01

    jika variabel kurang dari 4 huruf, maka ditambah X hingga 4 huruf.

    contoh: (nama toko ABC, author SUM, produk NAW)
    BLI-ABCX-SUMX-NAWXNAWX-01
     */

    public Product populateSKU(Product product){
        // Mencari Nama Toko
        String shopName = shopRepository.findFirstByShopId(product.getShop().getShopId()).getShopName();
        shopName = shopName.replaceAll("\\s+","");
        while (shopName.length() < 4){
            shopName = shopName + "X";
        }
        String skuShopName = shopName.substring(shopName.length() - 4);

        // Mencari Category
        String categoryName = productCategoryRepository.findFirstByProductCategoryId(
                product.getProductCategory().getProductCategoryId())
                .getProductCategoryName();
        categoryName = categoryName.replaceAll("\\s+", "");
        while (categoryName.length() < 4){
            categoryName = categoryName + "X";
        }
        String skuCategoryName = categoryName.substring(0, 3);

        // Mencari Nama Author
        String authorName = product.getProductAuthor();
        authorName = authorName.replaceAll("\\s", "");
        while (authorName.length() < 4){
            authorName = authorName + "X";
        }
        String skuAuthorName = authorName.substring(0, 4);

        // Mencari Nama Produk
        String productName = product.getProductName();
        productName =  productName.replaceAll("\\s+", "");
        while (productName.length() < 4){
            productName = productName + "X";
        }
        String skuProductName = productName.substring(0, 4) + productName.substring(productName.length() - 4);

        Integer count = 1;
        String sku = "BLI" + "-" + skuShopName + "-" + skuCategoryName + "-" +
                skuAuthorName + "-" + skuProductName + "-" + count;
        while(productRepository.existsByProductSku(sku)){
            count = count + 1;
            sku = "BLI" + "-" + skuShopName + "-" + skuCategoryName + "-" +
                    skuAuthorName + "-" + skuProductName + "-" + count;
        }
        sku = sku.toUpperCase();
        product.setProductSku(sku);
        return product;
    }

}
