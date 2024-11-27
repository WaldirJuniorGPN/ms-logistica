package com.techchallenge4.ms_logistica.unit.mapper;

import com.techchallenge4.ms_logistica.mapper.RastreamentoMapper;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtil;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RastreamentoMapperTest {

    private final RastreamentoMapper rastreamentoMapper = MapperUtils.rastreamentoMapper();

    @Nested
    class ToEntity {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rota = RotaUtils.buildRota();
            var request = RastreamentoUtil.buildRastreamentoRequest();

            // When
            var rastreamento = rastreamentoMapper.toEntity(request, rota);

            // Then
            assertNotNull(rastreamento);
            assertEquals(request.latitude(), rastreamento.getUltimaLatitude());
            assertEquals(request.longitude(), rastreamento.getUltimaLongitude());
        }
        @Test
        void shouldMapUltimaLatitude() {
            // Given
            var rota = RotaUtils.buildRota();
            var request = RastreamentoUtil.buildRastreamentoRequest().withLatitude(null);

            // When
            var rastreamento = rastreamentoMapper.toEntity(request, rota);

            // Then
            assertNull(rastreamento.getUltimaLatitude());
        }
        @Test
        void shouldMapUltimaLongitude() {
            // Given
            var rota = RotaUtils.buildRota();
            var request = RastreamentoUtil.buildRastreamentoRequest().withLongitude(null);

            // When
            var rastreamento = rastreamentoMapper.toEntity(request, rota);

            // Then
            assertNull(rastreamento.getUltimaLongitude());
        }
    }

    @Nested
    class UpdateUltimaPosicao {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();
            var request = RastreamentoUtil.buildRastreamentoRequest()
                    .withLatitude(2.0)
                    .withLongitude(2.0);

            // When
            rastreamentoMapper.updateUltimaPosicao(rastreamento, request);

            // Then
            assertEquals(request.latitude(), rastreamento.getUltimaLatitude());
            assertEquals(request.longitude(), rastreamento.getUltimaLongitude());
        }
        @Test
        void shouldIgnoreRota() {
            // Given
            var initialRotaId = 1L;
            var rastreamento = RastreamentoUtil.buildRastreamento();
            rastreamento.setId(initialRotaId);
            var request = RastreamentoUtil.buildRastreamentoRequest()
                    .withRotaId(rastreamento.getId() + 1);

            // When
            rastreamentoMapper.updateUltimaPosicao(rastreamento, request);

            // Then
            assertEquals(initialRotaId, rastreamento.getId());
        }
    }

    @Nested
    class ToResponse {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();

            // When
            var response = rastreamentoMapper.toResponse(rastreamento);

            // Then
            assertNotNull(response);
            assertEquals(rastreamento.getId(), response.id());
            assertEquals(rastreamento.getUltimaLatitude(), response.ultimaLatitude());
            assertEquals(rastreamento.getUltimaLongitude(), response.ultimaLongitude());
        }
        @Test
        void shouldMapRotaId() {
            // Given
            var rastreamento = RastreamentoUtil.buildRastreamento();

            // When
            var response = rastreamentoMapper.toResponse(rastreamento);

            // Then
            assertEquals(rastreamento.getRota().getId(), response.rotaId());
        }
    }

}