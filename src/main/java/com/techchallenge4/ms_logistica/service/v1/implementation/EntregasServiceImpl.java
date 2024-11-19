package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.client.PedidoServiceClient;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import com.techchallenge4.ms_logistica.repository.OrigemRepository;
import com.techchallenge4.ms_logistica.repository.ParadaRepository;
import com.techchallenge4.ms_logistica.service.v1.EntregasService;
import com.techchallenge4.ms_logistica.repository.EntregadorRepository;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntregasServiceImpl implements EntregasService {

    private final PedidoServiceClient pedidoServiceClient;
    private final RotaService rotaService;
    private final EntregadorRepository entregadorRepository;
    private final OrigemRepository origemRepository;
    private final ParadaRepository paradaRepository;

    @Override
    public void processDeliveriesForState(EstadoEnum estado) {
        log.info("Starting delivery processing for state: {}", estado);

        var pedidosPendentes = pedidoServiceClient.getPedidos(estado, PedidoStatusEnum.PENDENTE);

        if (pedidosPendentes.isEmpty()) {
            log.info("No uninitialized deliveries found for state: {}", estado);
            return;
        }

        var entregador = entregadorRepository.findByEstado(estado)
                .orElseThrow(() -> new RuntimeException("No Entregador found for state: " + estado));

        var origem = origemRepository.findByRegiao(estado.getRegiao())
                .orElseThrow(() -> new RuntimeException("No Origem found for state: " + estado));

        try {
            var optimizedRoute = rotaService.optimizeAndSaveRoute(entregador, origem, pedidosPendentes);

            optimizedRoute.getParadas().forEach(parada -> {
                parada.setStatus(PedidoStatusEnum.PREPARANDO);
                paradaRepository.save(parada);
            });

            log.info("Successfully processed deliveries for state: {}", estado);
        } catch (Exception e) {
            log.error("Error processing deliveries for state: {} - {}", estado, e.getMessage(), e);
        }
    }

}
