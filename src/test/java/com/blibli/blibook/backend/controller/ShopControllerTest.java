package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.model.entity.Shop;
import com.blibli.blibook.backend.service.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class ShopControllerTest {

    @Spy
    @InjectMocks
    ShopController shopController;

    @Mock
    ShopService shopService;

    @Mock
    ObjectMapper objectMapper;

    private Shop sampleShop = new Shop(
            1,
            "Sample Shop",
            "Sample Address",
            "Sample City",
            "Sample Province",
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
    void findByShopId() {
        // Given
        when(shopService.findByShopId(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = shopController.findByShopId(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void shopRegister() throws IOException {
        // Given
        doReturn(sampleShop).when(shopController).mapToShop(anyString());
        when(shopService.shopRegister(any(Shop.class), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = shopController.shopRegister(anyString(), anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void shopUpdate() throws IOException {
        // Given
        doReturn(sampleShop).when(shopController).mapToShop(anyString());
        when(shopService.updateShop(any(Shop.class))).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = shopController.shopUpdate(anyString());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findShopByUserId() {
        // Given
        when(shopService.findShopByUserId(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = shopController.findShopByUserId(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findAll() {
        // Given
        when(shopService.findAll()).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = shopController.findAll();

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void mapToShop() {
    }
}