package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.api.v1.response.RotaResponse;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.exception.ResourceNotFound;
import com.techchallenge4.ms_logistica.mapper.RotaMapper;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.service.v1.OpenRouteService;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RotaServiceImpl implements RotaService {

    private final OpenRouteService openRouteService;
    private final RotaMapper mapper;
    private final RotaRepository repository;

    @Override
    public RotaResponse findByPedidoId(Long pedidoId) {
        return repository.findByPedidoId(pedidoId)
                .map(mapper::toRotaResponse)
                .orElseThrow(() -> new ResourceNotFound("Rota não encontrada pelo pedidoId"));
    }

    @Override
    public List<RotaResponse> findByEntregadorId(Long entregadorId) {
        return getRotasByEntregadorId(entregadorId).stream()
                .map(mapper::toRotaResponse)
                .toList();
    }

    @Override
    public List<DirectionsResponse> findDirectionsByEntregadorId(Long entregadorId) {
        return getRotasByEntregadorId(entregadorId).stream()
                .map(openRouteService::getDirections)
                .toList();
    }

    @Override
    @Transactional
    public Rota optimizeAndSaveRoute(Entregador entregador, Origem origem, List<PedidoResponse> pedidosPendentes) {
        var optimizeRoute = openRouteService.getOptimizeRoute(pedidosPendentes, entregador, origem);
        return saveRouteAndStops(optimizeRoute, entregador, origem);
    }

    private Rota saveRouteAndStops(OptimizeResponse optimizeResponse, Entregador entregador, Origem origem) {
        return repository.save(mapToRota(optimizeResponse, entregador, origem));
    }

    private Rota mapToRota(OptimizeResponse optimizeResponse, Entregador entregador, Origem origem) {
        return mapper.toRota(optimizeResponse.getRoutes().getFirst(), entregador, origem);
    }

    private List<Rota> getRotasByEntregadorId(Long entregadorId) {
        return repository.findAllByEntregadorId(entregadorId)
                .filter(rotas -> !rotas.isEmpty())
                .orElseThrow(() -> new ResourceNotFound("Rotas não encontradas pelo entregadorId"));
    }

}
