package com.techchallenge4.ms_logistica.unit.service.v1.impl;

import com.techchallenge4.ms_logistica.client.OpenRouteClient;
import com.techchallenge4.ms_logistica.client.request.DirectionsRequest;
import com.techchallenge4.ms_logistica.client.request.OptimizeRequest;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.mapper.OpenRouteMapper;
import com.techchallenge4.ms_logistica.service.v1.OpenRouteService;
import com.techchallenge4.ms_logistica.service.v1.impl.OpenRouteServiceImpl;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.OpenRouteUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import com.techchallenge4.ms_logistica.utils.PedidoUtils;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtil;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OpenRouteServiceImplTest {

    private final OpenRouteMapper mapper = spy(MapperUtils.openRouteMapper());
    private final OpenRouteClient client = mock(OpenRouteClient.class);
    private final OpenRouteService service = new OpenRouteServiceImpl(mapper, client);

    @Nested
    class GetOptimizeRoute {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var pedidos = List.of(PedidoUtils.buildPedidoResponse());
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();
            var response = OpenRouteUtils.buildOptimizeResponse();

            when(client.getOptimizeRoute(any(OptimizeRequest.class))).thenReturn(response);

            // When
            var result = service.getOptimizeRoute(pedidos, entregador, origem);

            // Then
            assertNotNull(result);
            assertEquals(response, result);

            verify(mapper, times(1)).toOptimizeRequest(pedidos, entregador, origem);
            verify(client, times(1)).getOptimizeRoute(any(OptimizeRequest.class));
        }
    }

    @Nested
    class GetDirectionsByRota {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rota = RotaUtils.buildRota();
            var response = OpenRouteUtils.buildDirectionsResponse();

            when(client.getDirections(anyString(), any(DirectionsRequest.class))).thenReturn(response);

            // When
            var result = service.getDirectionsByRota(rota);

            // Then
            assertNotNull(result);
            assertEquals(response, result);

            verify(mapper, times(1)).toDirectionsRequestFromRota(rota);
            verify(client, times(1)).getDirections(anyString(), any(DirectionsRequest.class));
        }
    }

    @Nested
    class GetDirectionsByRastreamentoAndParada {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();
            var parada = ParadaUtils.buildParada();
            var response = new DirectionsResponse();

            when(client.getDirections(anyString(), any(DirectionsRequest.class))).thenReturn(response);

            // When
            var result = service.getDirectionsByRastreamentoAndParada(rastreamento, parada);

            // Then
            assertNotNull(result);
            assertEquals(response, result);

            verify(mapper, times(1)).toDirectionsRequestFromRastreamentoAndParada(rastreamento, parada);
            verify(client, times(1)).getDirections(anyString(), any(DirectionsRequest.class));
        }
    }
}