package com.techchallenge4.ms_logistica.unit.service.v1.impl;

import com.techchallenge4.ms_logistica.client.PedidoClient;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import com.techchallenge4.ms_logistica.exception.FinalizarEntregasException;
import com.techchallenge4.ms_logistica.exception.ProcessarEntregasException;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import com.techchallenge4.ms_logistica.service.v1.EntregasService;
import com.techchallenge4.ms_logistica.service.v1.OrigemService;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import com.techchallenge4.ms_logistica.service.v1.impl.EntregasServiceImpl;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import com.techchallenge4.ms_logistica.utils.PedidoUtils;
import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntregasServiceImplTest {

    private final PedidoClient pedidoClient = mock(PedidoClient.class);
    private final RotaService rotaService = mock(RotaService.class);
    private final EntregadorService entregadorService = mock(EntregadorService.class);
    private final OrigemService origemService = mock(OrigemService.class);
    private final EntregasService service = new EntregasServiceImpl(pedidoClient, rotaService, entregadorService, origemService);

    @Nested
    class ProcessarPedidosPorEstado {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var estado = EstadoEnum.SP;
            var pedidosPendentes = PedidoUtils.buildPedidoResponseList(3);
            var entregador = EntregadorUtils.buildEntregador();
            var origem = OrigemUtils.buildOrigem();

            when(pedidoClient.getPedidosByEstadoEStatus(estado, PedidoStatusEnum.PENDENTE)).thenReturn(pedidosPendentes);
            when(entregadorService.findByEstadoAndDisponibilidade(estado, true)).thenReturn(List.of(entregador));
            when(origemService.findByRegiao(estado.getRegiao())).thenReturn(origem);

            // When
            service.processarPedidosPorEstado(estado);

            // Then
            verify(pedidoClient, times(1)).getPedidosByEstadoEStatus(estado, PedidoStatusEnum.PENDENTE);
            verify(entregadorService, times(1)).findByEstadoAndDisponibilidade(estado, true);
            verify(origemService, times(1)).findByRegiao(estado.getRegiao());
            verify(rotaService, times(1)).optimizeAndSaveRoute(entregador, origem, pedidosPendentes);
            verify(entregadorService, times(1)).bloquearEntregador(entregador);
        }
        @Test
        void shouldNotExecuteWhenPedidosPendentesIsEmpty() {
            // Given
            var estado = EstadoEnum.SP;

            when(pedidoClient.getPedidosByEstadoEStatus(estado, PedidoStatusEnum.PENDENTE)).thenReturn(List.of());

            // When
            service.processarPedidosPorEstado(estado);

            // Then
            verify(pedidoClient, times(1)).getPedidosByEstadoEStatus(estado, PedidoStatusEnum.PENDENTE);
            verify(entregadorService, times(0)).findByEstadoAndDisponibilidade(estado, true);
            verify(origemService, times(0)).findByRegiao(estado.getRegiao());
            verify(rotaService, times(0)).optimizeAndSaveRoute(null, null, null);
            verify(entregadorService, times(0)).bloquearEntregador(null);
        }
        @Test
        void shouldHandleException() {
            // Given
            var estado = EstadoEnum.SP;

            when(pedidoClient.getPedidosByEstadoEStatus(estado, PedidoStatusEnum.PENDENTE)).thenThrow(new RuntimeException("Error"));

            // When / Then
            assertThrows(ProcessarEntregasException.class, () -> service.processarPedidosPorEstado(estado));

            verify(pedidoClient, times(1)).getPedidosByEstadoEStatus(estado, PedidoStatusEnum.PENDENTE);
        }
    }

    @Nested
    class FinalizarPedidos {
        @Test
        void shouldExecuteRotasFinalizadasSuccessfully() {
            // Given
            var rotas = RotaUtils.buildRotaList(3);
            rotas.forEach(rota -> rota.getParadas().forEach(parada -> parada.setStatus(PedidoStatusEnum.ENTREGUE)));

            when(rotaService.findByStatus(RotaStatusEnum.EM_ANDAMENTO)).thenReturn(rotas);

            // When
            service.finalizarPedidos();

            // Then
            verify(rotaService, times(1)).findByStatus(RotaStatusEnum.EM_ANDAMENTO);
            verify(rotaService, times(3)).finalizarRota(any(Rota.class));
        }
        @Test
        void shouldNotExecuteRotasPendentes() {
            // Given
            var rotas = RotaUtils.buildRotaList(3);
            rotas.forEach(rota -> rota.getParadas().forEach(parada -> parada.setStatus(PedidoStatusEnum.PENDENTE)));

            when(rotaService.findByStatus(RotaStatusEnum.EM_ANDAMENTO)).thenReturn(rotas);

            // When
            service.finalizarPedidos();

            // Then
            verify(rotaService, times(1)).findByStatus(RotaStatusEnum.EM_ANDAMENTO);
            verify(rotaService, times(0)).finalizarRota(any(Rota.class));
        }
        @Test
        void shouldHandleException() {
            // Given
            when(rotaService.findByStatus(RotaStatusEnum.EM_ANDAMENTO)).thenThrow(new RuntimeException("Error"));

            // When / Then
            assertThrows(FinalizarEntregasException.class, service::finalizarPedidos);

            verify(rotaService, times(1)).findByStatus(RotaStatusEnum.EM_ANDAMENTO);
        }
    }

}
