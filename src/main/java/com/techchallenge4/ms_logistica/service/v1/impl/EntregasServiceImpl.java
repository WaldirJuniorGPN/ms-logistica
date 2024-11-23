package com.techchallenge4.ms_logistica.service.v1.impl;

import com.techchallenge4.ms_logistica.client.PedidoServiceClient;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import com.techchallenge4.ms_logistica.exception.ProcessamentoEntregasException;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import com.techchallenge4.ms_logistica.service.v1.EntregasService;
import com.techchallenge4.ms_logistica.service.v1.OrigemService;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntregasServiceImpl implements EntregasService {

    private final PedidoServiceClient pedidoServiceClient;
    private final RotaService rotaService;
    private final EntregadorService entregadorService;
    private final OrigemService origemService;

    @Override
    @Transactional
    public void processarPedidosPorEstado(EstadoEnum estado) {
        try {
            log.info("Iniciando processamento de pedidos para o estado: {}", estado);

            var pedidosPendentes = pedidoServiceClient.getPedidosByEstadoEStatus(estado, PedidoStatusEnum.PENDENTE);

            if (pedidosPendentes.isEmpty()) {
                log.info("Nenhuma entrega pendente para o estado: {}", estado);
                return;
            }

            var entregador = entregadorService.findByEstadoAndDisponibilidade(estado, true).getFirst();

            var origem = origemService.findByRegiao(estado.getRegiao());

            rotaService.optimizeAndSaveRoute(entregador, origem, pedidosPendentes);

            entregadorService.bloquearEntregador(entregador);

        } catch (Exception e) {
            log.error("Erro ao processar pedidos para o estado: {}", estado, e);
            throw new ProcessamentoEntregasException(e);
        }
    }

    @Override
    @Transactional
    public void finalizarPedidos() {
        try {
            log.info("Liberando entregadores");

            rotaService.findByStatus(RotaStatusEnum.EM_ANDAMENTO).stream()
                    .filter(Rota::isRotaFinalizada)
                    .forEach(rotaService::finalizarRota);

        } catch (Exception e) {
            log.error("Erro ao liberar entregadores", e);
            throw new ProcessamentoEntregasException(e);
        }
    }

}
