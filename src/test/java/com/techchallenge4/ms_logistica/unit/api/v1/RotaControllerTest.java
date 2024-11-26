package com.techchallenge4.ms_logistica.unit.api.v1;

import com.techchallenge4.ms_logistica.api.v1.RotaController;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RotaController.class)
class RotaControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/rota";
    private static final String PEDIDO_URL = BASE_URL + "/pedido/{pedidoId}";
    private static final String ENTREGADOR_URL = BASE_URL + "/entregador/{entregadorId}";

    @MockBean
    private RotaService service;

    @Nested
    class GetRotaByPedidoId {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var pedidoId = 1L;
            var response = RotaUtils.buildRotaResponse();

            when(service.findByPedidoId(pedidoId)).thenReturn(response);

            // When
            var result = mockMvc.perform(get(PEDIDO_URL, pedidoId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).findByPedidoId(pedidoId);
        }

        @Test
        void shouldReturnNotFoundWhenResourceNotFound() throws Exception {
            // Given
            var pedidoId = 1L;

            when(service.findByPedidoId(pedidoId)).thenThrow(new ResourceNotFoundException("not found"));

            // When
            var result = mockMvc.perform(get(PEDIDO_URL, pedidoId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNotFound());
            verify(service).findByPedidoId(pedidoId);
        }
    }

    @Nested
    class GetRotaByEntregadorId {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var entregadorId = 1L;
            var response = List.of(RotaUtils.buildRotaResponse());

            when(service.findByEntregadorId(entregadorId)).thenReturn(response);

            // When
            var result = mockMvc.perform(get(ENTREGADOR_URL, entregadorId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).findByEntregadorId(entregadorId);
        }

        @Test
        void shouldReturnNotFoundWhenResourceNotFound() throws Exception {
            // Given
            var entregadorId = 1L;

            when(service.findByEntregadorId(entregadorId)).thenThrow(new ResourceNotFoundException("not found"));

            // When
            var result = mockMvc.perform(get(ENTREGADOR_URL, entregadorId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNotFound());
            verify(service).findByEntregadorId(entregadorId);
        }
    }
}