package com.techchallenge4.ms_logistica.unit.mapper;

import com.techchallenge4.ms_logistica.mapper.OpenRouteMapper;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import com.techchallenge4.ms_logistica.utils.PedidoUtils;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtils;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
            assertEquals(rota.getParadas().size(), request.getCoordinates().size());
        }
        @Test
        void shouldMapCoordinates() {
            // Given
            var rota = RotaUtils.buildRota();

            // When
            var coordinates = mapper.mapCoordinates(rota);

            // Then
            assertNotNull(coordinates);
            assertEquals(rota.getParadas().size(), coordinates.size());
        }
    }

    @Nested
    class ToDirectionsRequestFromRastreamentoAndParada {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rastreamento = RastreamentoUtils.buildRastreamento();
            var parada = ParadaUtils.buildParada();

            // When
            var request = mapper.toDirectionsRequestFromRastreamentoAndParada(rastreamento, parada);

            // Then
            assertNotNull(request);
            assertEquals(2, request.getCoordinates().size());
        }
        @Test
        void shouldMapCoordinates() {
            // Given
            var rastreamento = RastreamentoUtils.buildRastreamento();
            var parada = ParadaUtils.buildParada();

            // When
            var coordinates = mapper.mapCoordinates(rastreamento, parada);

            // Then
            assertNotNull(coordinates);
            assertEquals(2, coordinates.size());
        }
    }
}