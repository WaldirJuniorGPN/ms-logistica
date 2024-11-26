package com.techchallenge4.ms_logistica.unit.mapper;

import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.mapper.RotaMapper;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.OpenRouteUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RotaMapperTest {

    private final RotaMapper rotaMapper = MapperUtils.rotaMapper();

    @Nested
    class ToRotaResponse {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rota = RotaUtils.buildRota();

            // When
            var response = rotaMapper.toRotaResponse(rota);

            // Then
            assertNotNull(response);
            assertEquals(rota.getId(), response.id());
            assertEquals(rota.getStatus(), response.status());
            assertEquals(rota.getOrigem().getId(), response.origem().id());
            assertEquals(rota.getEntregador().getId(), response.entregador().id());
            assertEquals(rota.getParadas().size(), response.paradas().size());
        }
    }

    @Nested
    class ToRota {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var route = OpenRouteUtils.buildOptimizeRoute("job", List.of(10.0, 20.0), 1);
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            // When
            var rota = rotaMapper.toRota(route, entregador, origem);

            // Then
            assertNotNull(rota);
            assertEquals(entregador.getId(), rota.getEntregador().getId());
            assertEquals(origem.getId(), rota.getOrigem().getId());
            assertEquals(route.getSteps().size(), rota.getParadas().size());
        }

        @Test
        void shouldMapIdNull() {
            // Given
            var route = OpenRouteUtils.buildOptimizeRoute("job", List.of(10.0, 20.0), 1);
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            // When
            Rota rota = rotaMapper.toRota(route, entregador, origem);

            // Then
            assertNull(rota.getId());
        }

        @Test
        void shouldMapStatusConstant() {
            // Given
            var route = OpenRouteUtils.buildOptimizeRoute("job", List.of(10.0, 20.0), 1);
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            // When
            var rota = rotaMapper.toRota(route, entregador, origem);

            // Then
            assertEquals("PENDENTE", rota.getStatus().name());
        }

        @Test
        void shouldMapParadas() {
            // Given
            var route = OpenRouteUtils.buildOptimizeRoute("job", List.of(10.0, 20.0), 1);
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            // When
            var rota = rotaMapper.toRota(route, entregador, origem);

            // Then
            assertNotNull(rota.getParadas());
            assertFalse(rota.getParadas().isEmpty());
            assertEquals(route.getSteps().size(), rota.getParadas().size());
        }
    }
}