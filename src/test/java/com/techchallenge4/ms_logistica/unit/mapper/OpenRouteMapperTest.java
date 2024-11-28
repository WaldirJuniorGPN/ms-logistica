package com.techchallenge4.ms_logistica.unit.mapper;

import com.techchallenge4.ms_logistica.mapper.OpenRouteMapper;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import com.techchallenge4.ms_logistica.utils.PedidoUtils;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtil;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OpenRouteMapperTest {

    private final OpenRouteMapper mapper = MapperUtils.openRouteMapper();

    @Nested
    class ToOptimizeRequest {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var pedidoResponseList = PedidoUtils.buildPedidoResponseList(3);
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            // When
            var request = mapper.toOptimizeRequest(pedidoResponseList, entregador, origem);

            // Then
            assertNotNull(request);
            assertEquals(1, request.getVehicles().size());
            assertEquals(pedidoResponseList.size(), request.getJobs().size());
        }

        @Test
        void shouldReturnNullWhenPedidoResponseListIsNull() {
            // Given
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.toOptimizeRequest(null, entregador, origem));
        }

        @Test
        void shouldReturnNullWhenEntregadorIsNull() {
            // Given
            var pedidoResponseList = PedidoUtils.buildPedidoResponseList(3);
            var origem = OrigemUtils.buildOrigem();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.toOptimizeRequest(pedidoResponseList, null, origem));
        }

        @Test
        void shouldReturnNullWhenOrigemIsNull() {
            // Given
            var pedidoResponseList = PedidoUtils.buildPedidoResponseList(3);
            var entregador = EntregadorUtils.buildEntregador();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.toOptimizeRequest(pedidoResponseList, entregador, null));
        }

        @Test
        void shouldMapVehicle() {
            // Given
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            // When
            var vehicles = mapper.mapVehicle(entregador, origem);

            // Then
            assertNotNull(vehicles);
            assertEquals(1, vehicles.size());
            assertEquals(entregador.getId().toString(), vehicles.getFirst().getId());
        }

        @Test
        void shouldReturnNullWhenEntregadorIsNullInMapVehicle() {
            // Given
            var origem = OrigemUtils.buildOrigem();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.mapVehicle(null, origem));
        }

        @Test
        void shouldReturnNullWhenOrigemIsNullInMapVehicle() {
            // Given
            var entregador = EntregadorUtils.buildEntregador();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.mapVehicle(entregador, null));
        }

        @Test
        void shouldMapJobs() {
            // Given
            var pedidoResponses = PedidoUtils.buildPedidoResponseList(3);

            // When
            var jobs = mapper.mapJobs(pedidoResponses);

            // Then
            assertNotNull(jobs);
            assertEquals(pedidoResponses.size(), jobs.size());
            assertEquals(pedidoResponses.getFirst().id().toString(), jobs.getFirst().getId());
        }

        @Test
        void shouldReturnNullWhenPedidoResponsesIsNullInMapJobs() {
            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.mapJobs(null));
        }
    }

    @Nested
    class ToDirectionsRequestFromRota {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rota = RotaUtils.buildRota();

            // When
            var request = mapper.toDirectionsRequestFromRota(rota);

            // Then
            assertNotNull(request);
            assertEquals(rota.getParadas().size() + 1, request.getCoordinates().size());
        }

        @Test
        void shouldReturnNullWhenRotaIsNull() {
            // When
            var request = mapper.toDirectionsRequestFromRota(null);

            // Then
            assertNull(request);
        }

        @Test
        void shouldMapCoordinates() {
            // Given
            var rota = RotaUtils.buildRota();

            // When
            var coordinates = mapper.mapCoordinates(rota);

            // Then
            assertNotNull(coordinates);
            assertEquals(rota.getParadas().size() + 1, coordinates.size());
        }

        @Test
        void shouldReturnNullWhenRotaIsNullInMapCoordinates() {
            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.mapCoordinates(null));
        }
    }

    @Nested
    class ToDirectionsRequestFromRastreamentoAndParada {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();
            var parada = ParadaUtils.buildParada();

            // When
            var request = mapper.toDirectionsRequestFromRastreamentoAndParada(rastreamento, parada);

            // Then
            assertNotNull(request);
            assertEquals(2, request.getCoordinates().size());
        }

        @Test
        void shouldReturnNullWhenRastreamentoIsNull() {
            // Given
            var parada = ParadaUtils.buildParada();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.toDirectionsRequestFromRastreamentoAndParada(null, parada));
        }

        @Test
        void shouldReturnNullWhenParadaIsNull() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.toDirectionsRequestFromRastreamentoAndParada(rastreamento, null));
        }

        @Test
        void shouldMapCoordinates() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();
            var parada = ParadaUtils.buildParada();

            // When
            var coordinates = mapper.mapCoordinates(rastreamento, parada);

            // Then
            assertNotNull(coordinates);
            assertEquals(2, coordinates.size());
        }

        @Test
        void shouldReturnNullWhenRastreamentoIsNullInMapCoordinates() {
            // Given
            var parada = ParadaUtils.buildParada();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.mapCoordinates(null, parada));
        }

        @Test
        void shouldReturnNullWhenParadaIsNullInMapCoordinates() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.mapCoordinates(rastreamento, null));
        }
    }
}