package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Rota;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OtimizacaoRotaService {

    @Transactional
    Rota optimizeAndSaveRoute(Entregador entregador, List<PedidoResponse> pedidosPendentes);

}
