package com.techchallenge4.ms_logistica.unit.service.v1.impl;

import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.RotaMapper;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import com.techchallenge4.ms_logistica.service.v1.OpenRouteService;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import com.techchallenge4.ms_logistica.service.v1.impl.RotaServiceImpl;
import com.techchallenge4.ms_logistica.utils.OpenRouteUtils;
import com.techchallenge4.ms_logistica.utils.PedidoUtils;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RotaServiceImplTest {

    private final OpenRouteService openRouteService = mock(OpenRouteService.class);
    private final EntregadorService entregadorService = mock(EntregadorService.class);
    private final RotaMapper mapper = mock(RotaMapper.class);
    private final RotaRepository repository = mock(RotaRepository.class);
    private final RotaService service = new RotaServiceImpl(openRouteService, entregadorService, mapper, repository);

    @Nested
    class FindByPedidoId {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var pedidoId = 1L;
            var rota = RotaUtils.buildRota();

            when(repository.findByPedidoId(pedidoId)).thenReturn(Optional.of(rota));
            when(mapper.toRotaResponse(rota)).thenReturn(RotaUtils.buildRotaResponse());

            // When
            var result = service.findByPedidoId(pedidoId);

            // Then
            assertNotNull(result);
            assertEquals(RotaUtils.buildRotaResponse(), result);
            verify(repository, times(1)).findByPedidoId(pedidoId);
            verify(mapper, times(1)).toRotaResponse(rota);
        }

        @Test
        void shouldThrowResourceNotFoundExceptionWhenRotaNotFound() {
            // Given
            var pedidoId = 1L;

            when(repository.findByPedidoId(pedidoId)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findByPedidoId(pedidoId));
            verify(repository, times(1)).findByPedidoId(pedidoId);
        }
    }

    @Nested
    class FindByEntregadorId {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entregadorId = 1L;
            var rotas = RotaUtils.buildRotaList(3);

            when(repository.findAllByEntregadorId(entregadorId)).thenReturn(Optional.of(rotas));
            when(mapper.toRotaResponse(any(Rota.class))).thenReturn(RotaUtils.buildRotaResponse());

            // When
            var result = service.findByEntregadorId(entregadorId);

            // Then
            assertNotNull(result);
            assertEquals(3, result.size());
            verify(repository, times(1)).findAllByEntregadorId(entregadorId);
            verify(mapper, times(3)).toRotaResponse(any(Rota.class));
        }

        @Test
        void shouldThrowResourceNotFoundExceptionWhenRotaNotFound() {
            // Given
            var entregadorId = 1L;

            when(repository.findAllByEntregadorId(entregadorId)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findByEntregadorId(entregadorId));
            verify(repository, times(1)).findAllByEntregadorId(entregadorId);
        }
    }

    @Nested
    class OptimizeAndSaveRoute {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = new Entregador();
            var origem = new Origem();
            var pedidosPendentes = PedidoUtils.buildPedidoResponseList(3);
            var optimizeResponse = OpenRouteUtils.buildOptimizeResponse();
            var rota = RotaUtils.buildRota();

            when(openRouteService.getOptimizeRoute(pedidosPendentes, entregador, origem)).thenReturn(optimizeResponse);
            when(mapper.toRota(any(), eq(entregador), eq(origem))).thenReturn(rota);

            // When
            service.optimizeAndSaveRoute(entregador, origem, pedidosPendentes);

            // Then
            verify(openRouteService, times(1)).getOptimizeRoute(pedidosPendentes, entregador, origem);
            verify(repository, times(1)).save(rota);
        }
    }

    @Nested
    class FindEntityById {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rotaId = 1L;
            var rota = RotaUtils.buildRota();
            when(repository.findById(rotaId)).thenReturn(Optional.of(rota));

            // When
            var result = service.findEntityById(rotaId);

            // Then
            assertNotNull(result);
            assertEquals(rota, result);
            verify(repository, times(1)).findById(rotaId);
        }

        @Test
        void shouldThrowResourceNotFoundExceptionWhenRotaNotFound() {
            // Given
            var rotaId = 1L;
            when(repository.findById(rotaId)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findEntityById(rotaId));
            verify(repository, times(1)).findById(rotaId);
        }
    }

    @Nested
    class FindEntityByPedidoId {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var pedidoId = 1L;
            var rota = RotaUtils.buildRota();
            when(repository.findByPedidoId(pedidoId)).thenReturn(Optional.of(rota));

            // When
            var result = service.findEntityByPedidoId(pedidoId);

            // Then
            assertNotNull(result);
            assertEquals(rota, result);
            verify(repository, times(1)).findByPedidoId(pedidoId);
        }

        @Test
        void shouldThrowResourceNotFoundExceptionWhenRotaNotFound() {
            // Given
            var pedidoId = 1L;
            when(repository.findByPedidoId(pedidoId)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findEntityByPedidoId(pedidoId));
            verify(repository, times(1)).findByPedidoId(pedidoId);
        }
    }

    @Nested
    class FindEntitiesByEntregadorId {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entregadorId = 1L;
            var rotas = RotaUtils.buildRotaList(3);
            when(repository.findAllByEntregadorId(entregadorId)).thenReturn(Optional.of(rotas));

            // When
            var result = service.findEntitiesByEntregadorId(entregadorId);

            // Then
            assertNotNull(result);
            assertEquals(rotas, result);
            verify(repository, times(1)).findAllByEntregadorId(entregadorId);
        }

        @Test
        void shouldThrowResourceNotFoundExceptionWhenRotaNotFound() {
            // Given
            var entregadorId = 1L;
            when(repository.findAllByEntregadorId(entregadorId)).thenReturn(Optional.empty());

            // When & Then
            assertThrows(ResourceNotFoundException.class, () -> service.findEntitiesByEntregadorId(entregadorId));
            verify(repository, times(1)).findAllByEntregadorId(entregadorId);
        }
    }

    @Nested
    class FindByStatus {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var status = RotaStatusEnum.EM_ANDAMENTO;
            var rotas = RotaUtils.buildRotaList(3);
            when(repository.findAllByStatus(status)).thenReturn(rotas);

            // When
            var result = service.findByStatus(status);

            // Then
            assertNotNull(result);
            assertEquals(rotas, result);
            verify(repository, times(1)).findAllByStatus(status);
        }
    }

    @Nested
    class FinalizarRota {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = RotaUtils.buildRota();
            rota.setStatus(RotaStatusEnum.EM_ANDAMENTO);

            // When
            service.finalizarRota(rota);

            // Then
            assertEquals(RotaStatusEnum.FINALIZADA, rota.getStatus());
            verify(entregadorService, times(1)).liberarEntregador(rota.getEntregador());
            verify(repository, times(1)).save(rota);
        }
    }
}