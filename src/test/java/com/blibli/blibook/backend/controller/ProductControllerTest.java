package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Product;
import com.blibli.blibook.backend.service.ProductService;
import com.blibli.blibook.backend.service.impl.FileUploadServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ProductControllerTest {

    @Spy
    @InjectMocks
    ProductController productController;

    @Mock
    ProductService productService;

    @Mock
    ObjectMapper objectMapper;

    @Mock
    FileUploadServiceImpl fileUploadService;

    private Product sampleProduct = new Product(
            1,
            "Sample Book",
            "Sample Author",
            "10000000",
            "SAMPLE-SKU",
            "Sample Country",
            100000,
            "Sample Description",
            120,
            2019,
            "Indonesia",
            "Sample Variant",
            null,
            null,
            null,
            null,
            null,
            null,
            null
    );

    private ResponseDTO sampleResponseDTO = new ResponseDTO(
            200,
            "Success.",
            null
    );

    @Test
    void findByProductId() {
        // Given
        when(productService.findProductDetailById(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.findByProductId(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findByProductCategory() {
        // Given
        when(productService.findProductReviewByCategoryName(anyString())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.findByProductCategory(anyString());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findByShop() {
        // Given
        when(productService.findProductReviewByShopId(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.findByShop(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findByProductNameLike() {
        // Given
        when(productService.findProductReviewBySearchKey(anyString())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.findByProductNameLike(anyString());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findByProductPriceLessThan() {
        // Given
        when(productService.findProductReviewByPriceLessThan(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.findByProductPriceLessThan(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findProductByCountry() {
        // Given
        when(productService.findProductByCountry(anyString())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.findProductByCountry(anyString());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAll() {
        // Given
        when(productService.findAll()).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.findAll();

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void createProduct() {

    }


    @Test
    void deleteBlockProductByID() {
        // Given
        when(productService.deleteBlockProductByID(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = productController.deleteBlockProductByID(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void updateProductByID() {
    }
}