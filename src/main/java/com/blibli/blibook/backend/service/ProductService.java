package com.blibli.blibook.backend.service;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.dto.ProductDetailDTO;
import com.blibli.blibook.backend.dto.ProductReviewDTO;
import com.blibli.blibook.backend.model.entity.ProductCategory;
import com.blibli.blibook.backend.model.entity.ProductStatus;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.repository.ProductCategoryRepository;
import com.blibli.blibook.backend.repository.ProductRepository;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.blibli.blibook.backend.service.impl.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private FileUploadServiceImpl fileUploadServiceImpl;

    public Product findProductById(Integer productId){
        return productRepository.findFirstByProductId(productId);
    }

    public ProductDetailDTO findProductDetailById(Integer productId){
        return productServiceImpl.findProductDetail(productId);
    }

    public List<ProductReviewDTO> findProductReviewByCategoryName(String productCategoryName){
        List<Product> products = productRepository.findByProductCategory_ProductCategoryName(productCategoryName);
        return productServiceImpl.findProductReviewList(products);
    }

    public List<ProductReviewDTO> findProductReviewByShopId(Integer shopId){
        List<Product> products = productRepository.findByShop_ShopId(shopId);
        return productServiceImpl.findProductReviewList(products);
    }

    public List<ProductReviewDTO> findProductReviewBySearchKey(String searchKey){
        List<Product> products = productRepository.findByProductNameContaining(searchKey);
        return productServiceImpl.findProductReviewList(products);
    }

    public List<ProductReviewDTO> findProductReviewByPriceLessThan(Integer priceDemand){
        List<Product> products = productRepository.findByProductPriceLessThanEqual(priceDemand);
        return productServiceImpl.findProductReviewList(products);
    }

    public List<ProductReviewDTO> findProductByCountry(String productCountry) {
        List<Product> products = productRepository.findByProductCountry(productCountry);
        return productServiceImpl.findProductByCountry(products);
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product uploadProductPhoto(Integer productId, MultipartFile file) throws IOException {
        return fileUploadServiceImpl.uploadProductPhoto(productId, file);
    }

    public Product uploadProductItem(Integer productId, MultipartFile file) throws IOException {
        return fileUploadServiceImpl.uploadProductItem(productId, file);
    }

    public Optional<ProductCategory> findProductCategoryByProductCategoryName(String productCategoryName){
        return productServiceImpl.findProductCategoryByProductCategoryName(productCategoryName);
    }

    public Optional<ProductStatus> findProductStatusByProductStatusName(String productStatusName){
        return productServiceImpl.findProductStatusByProductStatusName(productStatusName);
    }

    public Optional<Shop> findShopByShopId(Integer shopId) {
        return productServiceImpl.findShopByShopId(shopId);
    }

    public ResponseDTO deleteProductByID(Integer productId) {
        ArrayList<Product> objProduct = new ArrayList<>();
        ResponseDTO response;

        try {
            Product product = productRepository.findFirstByProductId(productId);

            if (productRepository.deleteByProductId(productId) > 0) {
                objProduct.add(product);
                response = new ResponseDTO(200, "Success", objProduct);
            } else {
                response = new ResponseDTO(404, "ID Not Found!", null);
            }
        } catch (DataAccessException ex) {
            response = new ResponseDTO(500, ex.getCause().getMessage(), null);
        }

        return response;
    }

    public Product updateProductByID (Product product, String category) {
        Product temp = productRepository.findFirstByProductId(product.getProductId());

        temp.setProductAuthor(product.getProductAuthor());
        temp.setProductCountry(product.getProductCountry());
        temp.setProductDescription(product.getProductDescription());
        temp.setProductIsbn(product.getProductIsbn());
        temp.setProductLanguage(product.getProductLanguage());
        temp.setProductLength(product.getProductLength());
        temp.setProductName(product.getProductName());
        temp.setProductPrice(product.getProductPrice());
        temp.setProductReleaseYear(product.getProductReleaseYear());

        Optional<ProductCategory> productCategory = productServiceImpl.findProductCategoryByProductCategoryName(category);
        productCategory.ifPresent(temp::setProductCategory);

        return productRepository.save(temp);
    }

}
