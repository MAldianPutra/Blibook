package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.service.CartService;
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
class CartControllerTest {

    @Spy
    @InjectMocks
    CartController cartController;

    @Mock
    CartService cartService;

    private ResponseDTO sampleResponseDTO = new ResponseDTO(
            200,
            "Success.",
            null
    );

    @Test
    void findCartByUser() {
        // Given
        when(cartService.findByUserAndCartStatus(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = cartController.findCartByUser(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findWishlistByUser() {
        // Given
        when(cartService.findByUserAndCartStatus(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = cartController.findWishlistByUser(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void addCart() {
        // Given
        when(cartService.addCartOrWishlist(anyInt(), anyInt(), anyString())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = cartController.addCart(anyInt(), anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void addWishlist() {
        // Given
        when(cartService.addCartOrWishlist(anyInt(), anyInt(), anyString())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = cartController.addWishlist(anyInt(), anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void deleteWishlistCartUser() {
        // Given
        when(cartService.deleteWishlistCartUser(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = cartController.deleteWishlistCartUser(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }
}