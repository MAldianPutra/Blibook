package com.blibli.blibook.backend.controller;

import com.blibli.blibook.backend.dto.ResponseDTO;
import com.blibli.blibook.backend.service.OrderService;
import com.blibli.blibook.backend.service.PaymentService;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
class PaymentControllerTest {

    @Spy
    @InjectMocks
    PaymentController paymentController;

    @Mock
    PaymentService paymentService;

    private ResponseDTO sampleResponseDTO = new ResponseDTO(
            200,
            "Success.",
            null
    );

    @Test
    void paymentProduct() {
        // Given
        when(paymentService.paymentProduct(anyInt())).thenReturn(sampleResponseDTO);

        // When
        ResponseDTO testedResponseDTO = paymentController.paymentProduct(anyInt());

        // Assert
        assertEquals(sampleResponseDTO, testedResponseDTO);
    }

}