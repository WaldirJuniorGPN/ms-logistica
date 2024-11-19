package com.techchallenge4.ms_logistica.client.fallback;

import com.techchallenge4.ms_logistica.client.PedidoServiceClient;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.enums.StateEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class PedidoServiceClientFallback implements PedidoServiceClient {

    @Override
    public List<PedidoResponse> getPedidos(StateEnum cep, PedidoStatusEnum status) {
        log.error("Error fetching pedidos from PedidoServiceClient for cep: {} and status: {}", cep, status);
        return Collections.emptyList();
    }

}
