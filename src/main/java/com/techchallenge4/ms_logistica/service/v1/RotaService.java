package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.api.v1.response.RotaResponse;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;

import java.util.List;

public interface RotaService {

    RotaResponse findByPedidoId(Long pedidoId);

    List<RotaResponse> findByEntregadorId(Long entregadorId);

    void optimizeAndSaveRoute(Entregador entregador, Origem origem, List<PedidoResponse> pedidosPendentes);

    Rota findEntityById(Long rotaId);

    Rota findEntityByPedidoId(Long pedidoId);

    List<Rota> findEntitiesByEntregadorId(Long entregadorId);

    List<Rota> findByStatus(RotaStatusEnum rotaStatusEnum);

    void finalizarRota(Rota rota);

}
