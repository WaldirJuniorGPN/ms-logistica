package com.techchallenge4.ms_logistica.client.fallback;

import com.techchallenge4.ms_logistica.client.PedidoClient;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class PedidoClientFallback implements PedidoClient {

    @Override
    public List<PedidoResponse> getPedidosByEstadoEStatus(EstadoEnum estado, PedidoStatusEnum status) {
        log.error("Error fetching pedidos from PedidoServiceClient for estado: {} and status: {}", estado, status);
        return Collections.emptyList();
    }

    @Override
    public PedidoResponse updatePedidoStatus(Long id, PedidoStatusEnum status) {
        log.error("Error updating pedido status from PedidoServiceClient for pedidoId: {} and status: {}", id, status);
        return null;
    }

}
