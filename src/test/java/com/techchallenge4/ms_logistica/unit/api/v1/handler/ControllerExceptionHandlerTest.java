package com.techchallenge4.ms_logistica.unit.api.v1.handler;

import com.techchallenge4.ms_logistica.api.v1.EntregadorController;
import com.techchallenge4.ms_logistica.api.v1.handler.ControllerExceptionHandler;
import com.techchallenge4.ms_logistica.exception.FeignClientException;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({EntregadorController.class, ControllerExceptionHandler.class})
class ControllerExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntregadorService service;

    @Nested
    class HandleFeignClientException {
        @Test
        void shouldReturnFeignClientException() throws Exception {
            // Given
            doThrow(new FeignClientException("Invalid request", 400)).when(service).findById(1L);

            // When
            var result = mockMvc.perform(get("/entregador/1")
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isBadRequest());
        }
    }

    @Nested
    class HandleResourceNotFoundException {
        @Test
        void shouldReturnResourceNotFoundException() throws Exception {
            // Given
            doThrow(new ResourceNotFoundException("Resource not found")).when(service).findById(1L);

            // When
            var result = mockMvc.perform(get("/entregador/1")
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNotFound());
        }
    }

    @Nested
    class HandleGeneralException {
        @Test
        void shouldReturnGeneralException() throws Exception {
            // Given
            doThrow(new RuntimeException("Internal Server Error")).when(service).findById(1L);

            // When
            var result = mockMvc.perform(get("/entregador/1")
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isInternalServerError());
        }
    }

    @Nested
    class HandleBadRequestExceptions {
        @Test
        void shouldReturnBadRequestException() throws Exception {
            // Given
            doThrow(new IllegalArgumentException("Bad request")).when(service).findById(1L);

            // When
            var result = mockMvc.perform(get("/entregador/1")
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isBadRequest());
        }
    }
}