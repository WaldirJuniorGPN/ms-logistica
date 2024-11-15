package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.client.OpenRouteServiceClient;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.mapper.OptimizeMapper;
import com.techchallenge4.ms_logistica.mapper.RouteMapper;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.service.v1.OtimizacaoRotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OtimizacaoRotaServiceImpl implements OtimizacaoRotaService {

    private final OpenRouteServiceClient openRouteServiceClient;
    private final OptimizeMapper optimizeMapper;
    private final RouteMapper routeMapper;
    private final RotaRepository rotaRepository;

    @Override
    @Transactional
    public Rota optimizeAndSaveRoute(Entregador entregador, List<PedidoResponse> pedidosPendentes) {
        var origem = entregador.getOrigem();
        var optimizeRequest = optimizeMapper.toOptimizeRequest(pedidosPendentes, entregador, origem);
        var optimizeResponse = openRouteServiceClient.optimizeRoute(optimizeRequest);
        return saveRouteAndStops(optimizeResponse, origem);
    }

    private Rota saveRouteAndStops(OptimizeResponse optimizeResponse, Origem origem) {
        var rota = mapToRota(optimizeResponse, origem);
        return rotaRepository.save(rota);
    }

    private Rota mapToRota(OptimizeResponse optimizeResponse, Origem origem) {
        var route = optimizeResponse.getRoutes().getFirst();
        var rota = routeMapper.toRota(route);
        rota.setOrigem(origem);
        return rota;
    }
}
