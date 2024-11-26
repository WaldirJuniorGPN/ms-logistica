package com.techchallenge4.ms_logistica.unit.api.v1;

import com.techchallenge4.ms_logistica.api.v1.RastreamentoController;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.service.v1.RastreamentoService;
import com.techchallenge4.ms_logistica.utils.OpenRouteUtils;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtil;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RastreamentoController.class)
class RastreamentoControllerTest extends AbstractControllerTest {

    private static final String BASE_URL = "/rastreamento";
    private static final String ENTREGADOR_URL = BASE_URL + "/entregador/{entregadorId}";
    private static final String PEDIDO_URL = BASE_URL + "/pedido/{pedidoId}";

    @MockBean
    private RastreamentoService service;

    @Nested
    class CreateOrUpdateRastreamento {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var request = RastreamentoUtil.buildRastreamentoRequest();
            var response = RastreamentoUtil.buildRastreamentoResponse();

            when(service.createOrUpdateRastreamento(request)).thenReturn(response);

            // When
            var result = mockMvc.perform(post(BASE_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(request)));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).createOrUpdateRastreamento(request);
        }
    }

    @Nested
    class GetDirectionsByEntregadorId {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var entregadorId = 1L;
            var response = List.of(OpenRouteUtils.buildDirectionsResponse());

            when(service.findDirectionsByEntregadorId(entregadorId)).thenReturn(response);

            // When
            var result = mockMvc.perform(get(ENTREGADOR_URL, entregadorId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).findDirectionsByEntregadorId(entregadorId);
        }

        @Test
        void shouldReturnNotFoundWhenResourceNotFoundException() throws Exception {
            // Given
            var entregadorId = 1L;

            when(service.findDirectionsByEntregadorId(entregadorId)).thenThrow(new ResourceNotFoundException("not found"));

            // When
            var result = mockMvc.perform(get(ENTREGADOR_URL, entregadorId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNotFound());
            verify(service).findDirectionsByEntregadorId(entregadorId);
        }
    }

    @Nested
    class GetDirectionsByPedidoId {
        @Test
        void shouldReturnSuccessfully() throws Exception {
            // Given
            var pedidoId = 1L;
            var response = OpenRouteUtils.buildDirectionsResponse();

            when(service.findDirectionsByPedidoId(pedidoId)).thenReturn(response);

            // When
            var result = mockMvc.perform(get(PEDIDO_URL, pedidoId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isOk())
                    .andExpect(content().json(objectMapper.writeValueAsString(response)));
            verify(service).findDirectionsByPedidoId(pedidoId);
        }

        @Test
        void shouldReturnNotFoundWhenResourceNotFoundException() throws Exception {
            // Given
            var pedidoId = 1L;

            when(service.findDirectionsByPedidoId(pedidoId)).thenThrow(new ResourceNotFoundException("not found"));

            // When
            var result = mockMvc.perform(get(PEDIDO_URL, pedidoId)
                    .contentType(MediaType.APPLICATION_JSON));

            // Then
            result.andExpect(status().isNotFound());
            verify(service).findDirectionsByPedidoId(pedidoId);
        }
    }
}