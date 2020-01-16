package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.service.OrderService;
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
class OrderControllerTest {

    @Spy
    @InjectMocks
    OrderController orderController;

    @Mock
    OrderService orderService;

    private ResponseDTO sampleResponseDTO = new ResponseDTO(
            200,
            "Success.",
            null
    );

    @Test
    void userOrderNotPaid() {
        // Given
        when(orderService.findByUserIdAndOrderStatusId(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.userOrderNotPaid(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void shopOrderNotPaid() {
        // Given
        when(orderService.findByShopIdAndOrderStatusId(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.shopOrderNotPaid(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void userOrderWaitingConfirm() {
        // Given
        when(orderService.findByUserIdAndOrderStatusId(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.userOrderWaitingConfirm(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void findOrderWaitingShopId() {
        // Given
        when(orderService.findOrderWaitingShopId(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.findOrderWaitingShopId(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void userLibrary() {
        // Given
        when(orderService.findUserLibrary(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.userLibrary(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void orderedProductById() {
        // Given
        when(orderService.orderedProductById(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.orderedProductById(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void initiateOrder() {
        // Given
        when(orderService.initiateOrder(anyInt(), anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.initiateOrder(anyInt(), anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

    @Test
    void confirmOrder() {
        // Given
        when(orderService.confirmOrder(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = orderController.confirmOrder(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }
}