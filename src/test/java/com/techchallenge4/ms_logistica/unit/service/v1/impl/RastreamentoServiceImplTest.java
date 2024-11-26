package com.techchallenge4.ms_logistica.unit.service.v1.impl;

import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.RastreamentoMapper;
import com.techchallenge4.ms_logistica.repository.RastreamentoRepository;
import com.techchallenge4.ms_logistica.service.v1.OpenRouteService;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import com.techchallenge4.ms_logistica.service.v1.impl.RastreamentoServiceImpl;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.OpenRouteUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtil;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RastreamentoServiceImplTest {

    private final RastreamentoRepository repository = mock(RastreamentoRepository.class);
    private final RastreamentoMapper mapper = spy(MapperUtils.rastreamentoMapper());
    private final OpenRouteService openRouteService = mock(OpenRouteService.class);
    private final RotaService rotaService = mock(RotaService.class);
    private final RastreamentoServiceImpl service = new RastreamentoServiceImpl(repository, mapper, openRouteService, rotaService);

    @Nested
    class CreateOrUpdateRastreamento {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var request = RastreamentoUtil.buildRastreamentoRequest();
            var rastreamento = RastreamentoUtil.buildRastreamento();
            var response = RastreamentoUtil.buildRastreamentoResponse();

            when(rotaService.findEntityById(request.rotaId())).thenReturn(rastreamento.getRota());
            when(repository.findByRota(rastreamento.getRota())).thenReturn(java.util.Optional.of(rastreamento));
            when(repository.save(rastreamento)).thenReturn(rastreamento);

            // When
            var result = service.createOrUpdateRastreamento(request);

            // Then
            assertNotNull(result);
            assertEquals(response, result);

            verify(repository, times(1)).save(rastreamento);
            verify(mapper, times(1)).toResponse(rastreamento);
        }
    }

    @Nested
    class FindDirectionsByEntregadorId {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entregadorId = 1L;
            var rota = RotaUtils.buildRota();
            var directionsResponse = OpenRouteUtils.buildDirectionsResponse();

            when(rotaService.findEntitiesByEntregadorId(entregadorId)).thenReturn(List.of(rota));
            when(openRouteService.getDirectionsByRota(rota)).thenReturn(directionsResponse);

            // When
            var result = service.findDirectionsByEntregadorId(entregadorId);

            // Then
            assertNotNull(result);
            assertEquals(List.of(directionsResponse), result);

            verify(rotaService, times(1)).findEntitiesByEntregadorId(entregadorId);
            verify(openRouteService, times(1)).getDirectionsByRota(rota);
        }
        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var entregadorId = 1L;

            when(rotaService.findEntitiesByEntregadorId(entregadorId)).thenThrow(new ResourceNotFoundException("not found"));

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findDirectionsByEntregadorId(entregadorId));

            verify(rotaService, times(1)).findEntitiesByEntregadorId(entregadorId);
        }
    }

    @Nested
    class FindDirectionsByPedidoId {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var pedidoId = 1L;
            var rota = RotaUtils.buildRota();
            var rastreamento = RastreamentoUtil.buildRastreamento();
            var parada = rota.getParadaByPedidoId(pedidoId).orElse(ParadaUtils.buildParada());
            var directionsResponse = OpenRouteUtils.buildDirectionsResponse();

            when(rotaService.findEntityByPedidoId(pedidoId)).thenReturn(rota);
            when(repository.findByRota(rota)).thenReturn(Optional.of(rastreamento));
            when(openRouteService.getDirectionsByRastreamentoAndParada(rastreamento, parada)).thenReturn(directionsResponse);

            // When
            var result = service.findDirectionsByPedidoId(pedidoId);

            // Then
            assertNotNull(result);
            assertEquals(directionsResponse, result);

            verify(rotaService, times(1)).findEntityByPedidoId(pedidoId);
            verify(repository, times(1)).findByRota(rota);
            verify(openRouteService, times(1)).getDirectionsByRastreamentoAndParada(rastreamento, parada);
        }
        @Test
        void shouldThrowResourceNotFoundExceptionWhenRotaNotFound() {
            // Given
            var pedidoId = 1L;
            var rota = RotaUtils.buildRota();

            when(rotaService.findEntityByPedidoId(pedidoId)).thenThrow(new ResourceNotFoundException("not found"));

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findDirectionsByPedidoId(pedidoId));

            verify(rotaService, times(1)).findEntityByPedidoId(pedidoId);
        }
        @Test
        void shouldThrowResourceNotFoundExceptionWhenRastreamentoNotFound() {
            // Given
            var pedidoId = 1L;
            var rota = RotaUtils.buildRota();

            when(rotaService.findEntityByPedidoId(pedidoId)).thenReturn(rota);
            when(repository.findByRota(rota)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findDirectionsByPedidoId(pedidoId));

            verify(rotaService, times(1)).findEntityByPedidoId(pedidoId);
            verify(repository, times(1)).findByRota(rota);
        }
        @Test
        void shouldThrowResourceNotFoundExceptionWhenParadaNotFound() {
            // Given
            var pedidoId = 10L;
            var rota = RotaUtils.buildRota();
            var rastreamento = RastreamentoUtil.buildRastreamento();

            when(rotaService.findEntityByPedidoId(pedidoId)).thenReturn(rota);
            when(repository.findByRota(rota)).thenReturn(Optional.of(rastreamento));

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findDirectionsByPedidoId(pedidoId));

            verify(rotaService, times(1)).findEntityByPedidoId(pedidoId);
            verify(repository, times(1)).findByRota(rota);
        }
    }
}