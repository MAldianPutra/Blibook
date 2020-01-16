package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.service.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class AdminControllerTest {

    @Spy
    @InjectMocks
    AdminController adminController;

    @Mock
    UserService userService;

    @Mock
    ShopService shopService;

    @Mock
    ProductService productService;

    @Mock
    CartService cartService;

    @Mock
    OrderService orderService;

    @Mock
    PaymentService paymentService;

    private ResponseDTO sampleResponseDTO = new ResponseDTO(
            200,
            "Success.",
            null
    );

    @Test
    void findAllUserWithPaging() {
        // Given
        when(userService.findAllUserWithPaging(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = adminController.findAllUserWithPaging(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAllShopWithPaging() {
        // Given
        when(shopService.findAllShopWithPaging(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = adminController.findAllShopWithPaging(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAllProductWithPaging() {
        // Given
        when(productService.findAllProductWithPaging(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = adminController.findAllProductWithPaging(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAllCart() {
        // Given
        when(cartService.findAll()).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = adminController.findAllCart();

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAllOrder() {
        // Given
        when(orderService.findAll()).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = adminController.findAllOrder();

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAllPayment() {
        // Given
        when(paymentService.findAll()).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = adminController.findAllPayment();

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void populateAllSKU() {
        // Given
        when(productService.populateAllSKU()).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = adminController.populateAllSKU();

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }
}