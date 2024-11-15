package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Rota;

import java.util.List;

public interface OpenRouteService {

    OptimizeResponse getOptimizeRoute(List<PedidoResponse> pedidos, Entregador entregador, Origem origem);

    DirectionsResponse getDirections(Rota rota);

}
