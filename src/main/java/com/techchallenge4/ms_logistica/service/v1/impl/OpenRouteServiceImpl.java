package com.techchallenge4.ms_logistica.service.v1.impl;

import com.techchallenge4.ms_logistica.client.OpenRouteClient;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Parada;
import com.techchallenge4.ms_logistica.domain.Rastreamento;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.mapper.OpenRouteMapper;
import com.techchallenge4.ms_logistica.service.v1.OpenRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenRouteServiceImpl implements OpenRouteService {

    private static final String DEFAULT_PROFILE = "driving-car";

    private final OpenRouteMapper mapper;
    private final OpenRouteClient client;

    @Override
    public OptimizeResponse getOptimizeRoute(List<PedidoResponse> pedidos, Entregador entregador, Origem origem) {
        return client.getOptimizeRoute(mapper.toOptimizeRequest(pedidos, entregador, origem));
    }

    @Override
    public DirectionsResponse getDirectionsByRota(Rota rota) {
        rota.getParadas().sort(Comparator.comparing(Parada::getSequencia));
        return client.getDirections(DEFAULT_PROFILE, mapper.toDirectionsRequestFromRota(rota));
    }

    @Override
    public DirectionsResponse getDirectionsByRastreamentoAndParada(Rastreamento rastreamento, Parada parada) {
        return client.getDirections(DEFAULT_PROFILE, mapper.toDirectionsRequestFromRastreamentoAndParada(rastreamento, parada));
    }

}
