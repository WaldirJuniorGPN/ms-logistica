package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.client.OpenRouteServiceClient;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.mapper.OpenRouteServiceMapper;
import com.techchallenge4.ms_logistica.service.v1.OpenRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenRouteServiceImpl implements OpenRouteService {

    private static final String DEFAULT_PROFILE = "driving-car";

    private final OpenRouteServiceMapper mapper;
    private final OpenRouteServiceClient client;

    @Override
    public OptimizeResponse getOptimizeRoute(List<PedidoResponse> pedidos, Entregador entregador, Origem origem) {
        return client.getOptimizeRoute(mapper.toOptimizeRequest(pedidos, entregador, origem));
    }

    @Override
    public DirectionsResponse getDirections(Rota rota) {
        return client.getDirections(DEFAULT_PROFILE, mapper.toDirectionsRequest(rota));
    }

}
