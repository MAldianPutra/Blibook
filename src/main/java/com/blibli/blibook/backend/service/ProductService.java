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
import com.blibli.blibook.backend.service.impl.SearchKeyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.crypto.Data;
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
    private FileUploadServiceImpl fileUploadService;

    @Autowired
    private SearchKeyServiceImpl searchKeyService;

    public Product findProductById(Integer productId){
        return productRepository.findFirstByProductId(productId);
    }

    public ProductDetailDTO findProductDetailById(Integer productId){
        return productServiceImpl.findProductDetail(productId);
    }

    public ResponseDTO findProductReviewByCategoryName(String productCategoryName){
        try {
            List<Product> products = productRepository.findByProductCategory_ProductCategoryNameAndProductStatus_ProductStatusName(
                    productCategoryName, "AVAILABLE"
            );
            ArrayList<ProductReviewDTO> productReviewDTOS = (ArrayList<ProductReviewDTO>) productServiceImpl.findProductReviewList(products);
            if(!productReviewDTOS.isEmpty()){
                return new ResponseDTO(200, "Success", productReviewDTOS);
            }else {
                return new ResponseDTO(404, "Data not found", null);
            }

        }catch (DataAccessException ex) {
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }

    public ResponseDTO findProductReviewByShopId(Integer shopId){
        try {
            List<Product> products = productRepository.findByShop_ShopIdAndProductStatus_ProductStatusName(
                    shopId, "AVAILABLE");
            ArrayList<ProductReviewDTO> productReviewDTOS = (ArrayList<ProductReviewDTO>) productServiceImpl.findProductReviewList(products);
            if(!productReviewDTOS.isEmpty()){
                return new ResponseDTO(200, "Success", productReviewDTOS);
            }else {
                return new ResponseDTO(404, "Data not found", null);
            }
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }

    }

    public ResponseDTO findProductReviewBySearchKey(String searchKey){
        try {
            List<Product> products = searchKeyService.findProduct(searchKey);
            ArrayList<ProductReviewDTO> productReviewDTOS = (ArrayList<ProductReviewDTO>) productServiceImpl.findProductReviewList(products);
            if(!productReviewDTOS.isEmpty()){
                return new ResponseDTO(200, "Success", productReviewDTOS);
            }else {
                return new ResponseDTO(404, "Data not found", null);
            }
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }

    public ResponseDTO findProductReviewByPriceLessThan(Integer priceDemand){
        try {
            List<Product> products = productRepository.findByProductPriceLessThanEqualAndProductStatus_ProductStatusName(
                    priceDemand, "AVAILABLE");
            ArrayList<ProductReviewDTO> productReviewDTOS = (ArrayList<ProductReviewDTO>) productServiceImpl.findProductReviewList(products);
            if(!productReviewDTOS.isEmpty()){
                return new ResponseDTO(200, "Success", productReviewDTOS);
            }else {
                return new ResponseDTO(404, "Data not found", null);
            }
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }

    public ResponseDTO findProductByCountry(String productCountry) {
        try {
            List<Product> products = productRepository.findByProductCountryAndProductStatus_ProductStatusName(
                    productCountry, "AVAILABLE");
            ArrayList<ProductReviewDTO> productReviewDTOS = (ArrayList<ProductReviewDTO>) productServiceImpl.findProductByCountry(products);
            if(!productReviewDTOS.isEmpty()){
                return new ResponseDTO(200, "Success", productReviewDTOS);
            }else {
                return new ResponseDTO(404, "Data not found", null);
            }
        }catch (DataAccessException ex){
            return new ResponseDTO(400, ex.getMessage(), null);
        }
    }

    public ResponseDTO findAll(Integer page){
        Page<Product> productPage = productRepository.findAll(PageRequest.of(page, 20, Sort.by("productName").ascending()));
        return productServiceImpl.findAll(productPage);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public void uploadProductPhoto(Integer productId, MultipartFile file) throws IOException {
        fileUploadService.uploadProductPhoto(productId, file);
    }

    public void uploadProductItem(Integer productId, MultipartFile file) throws IOException {
        fileUploadService.uploadProductItem(productId, file);
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

    public Product populateSKU(Product product){
        return productServiceImpl.populateSKU(product);
    }

    public ResponseDTO populateAllSKU() {
        ResponseDTO responseDTO;
        try {
            List<Product> products = productRepository.findAll();
            ArrayList<Product> objProduct = new ArrayList<>();
            for (Product product : products) {
                product = productServiceImpl.populateSKU(product);
                productRepository.save(product);
                objProduct.add(product);
            }
            responseDTO = new ResponseDTO(200, "Success", objProduct);
        }catch(DataAccessException ex){
                responseDTO = new ResponseDTO(500, ex.getMessage(), null);
            }
        return responseDTO;
    }
    public ResponseDTO deleteBlockProductByID(Integer productId) {
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
            Product product = productRepository.findFirstByProductId(productId);
            productServiceImpl.blockProduct(product);
            objProduct.add(product);
            response = new ResponseDTO(200, "Success Block Product", objProduct);
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