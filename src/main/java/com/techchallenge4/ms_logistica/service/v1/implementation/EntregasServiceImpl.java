package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.client.PedidoServiceClient;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
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
    public void processDeliveriesForState(EstadoEnum estado) {
        try {
            log.info("Starting delivery processing for state: {}", estado);

            var pedidosPendentes = pedidoServiceClient.getPedidos(estado, PedidoStatusEnum.PENDENTE);

            if (pedidosPendentes.isEmpty()) {
                log.info("No uninitialized deliveries found for state: {}", estado);
                return;
            }

            var entregador = entregadorService.findByEstadoAndDisponibilidade(estado, true).getFirst();

            var origem = origemService.findByRegiao(estado.getRegiao());

            rotaService.optimizeAndSaveRoute(entregador, origem, pedidosPendentes);

            entregadorService.bloquearEntregador(entregador);
        } catch (Exception e) {
            log.error("Error processing deliveries for state: {}", estado, e);
            throw new ProcessamentoEntregasException(e);
        }
    }

}
