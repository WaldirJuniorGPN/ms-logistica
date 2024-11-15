package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.client.PedidoServiceClient;
import com.techchallenge4.ms_logistica.enums.CepEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import com.techchallenge4.ms_logistica.repository.ParadaRepository;
import com.techchallenge4.ms_logistica.service.v1.EntregasService;
import com.techchallenge4.ms_logistica.repository.EntregadorRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EntregasServiceImpl implements EntregasService {

    private final PedidoServiceClient pedidoServiceClient;
    private final OtimizacaoRotaServiceImpl otimizacaoRotaServiceImpl;
    private final EntregadorRepository entregadorRepository;
    private final ParadaRepository paradaRepository;

    @Override
    public void processDeliveriesForState(CepEnum state) {
        log.info("Starting delivery processing for state: {}", state);

        var pedidosPendentes = pedidoServiceClient.getPedidos(state, PedidoStatusEnum.PENDENTE);

        if (pedidosPendentes.isEmpty()) {
            log.info("No uninitialized deliveries found for state: {}", state);
            return;
        }

        var entregador = entregadorRepository.findByOrigemCepEnum(state)
                .orElseThrow(() -> new RuntimeException("No Entregador found for state: " + state));

        try {
            var optimizedRoute = otimizacaoRotaServiceImpl.optimizeAndSaveRoute(entregador, pedidosPendentes);

            optimizedRoute.getParadas().forEach(parada -> {
                parada.setStatus(PedidoStatusEnum.PREPARANDO);
                paradaRepository.save(parada);
            });

            log.info("Successfully processed deliveries for state: {}", state);
        } catch (Exception e) {
            log.error("Error processing deliveries for state: {} - {}", state, e.getMessage(), e);
        }
    }

}
