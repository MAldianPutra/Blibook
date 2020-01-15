package com.blibli.blibook.backend.controllerTests;

import com.blibli.blibook.backend.controller.ProductController;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.service.ProductService;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class ProductControllerTest {

    @Mock
    ProductService productService;

    @Mock
    FileUploadServiceImpl fileUploadService;

    @InjectMocks
    ProductController productController;

//    Product sampleProduct = new Product(
//            1,
//            "Book Name",
//            "Author",
//            "123123",
//            "BLI-ALDI-NOV-BOOKNAME-01",
//            "Indonesia",
//            50000,
//            "Sample book for testing.",
//            100,
//            2019,
//            "Indonesia",
//            "First Ed.",
//            null,
//            null
//    );

    @Test
    void findByProductId() {

    }

    @Test
    void findByProductCategory() {
    }

    @Test
    void findByShop() {
    }

    @Test
    void findByProductNameLike() {
    }

    @Test
    void findByProductPriceLessThan() {
    }

    @Test
    void findProductByCountry() {
    }

    @Test
    void findAllWithPaging() {
    }

    @Test
    void findAll() {
    }

    @Test
    void createProduct() {
    }

    @Test
    void populateAllSKU() {
    }

    @Test
    void deleteBlockProductByID() {
    }

    @Test
    void updateProductByID() {
    }
}