package com.techchallenge4.ms_logistica.service.v1.impl;

import com.techchallenge4.ms_logistica.api.v1.response.RotaResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.RotaMapper;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
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
    private final EntregadorService entregadorService;
    private final RotaMapper mapper;
    private final RotaRepository repository;

    @Override
    public RotaResponse findByPedidoId(Long pedidoId) {
        return repository.findByPedidoId(pedidoId)
                .map(mapper::toRotaResponse)
                .orElseThrow(() -> new ResourceNotFoundException("Rota não encontrada pelo pedidoId"));
    }

    @Override
    public List<RotaResponse> findByEntregadorId(Long entregadorId) {
        return getByEntregadorId(entregadorId).stream()
                .map(mapper::toRotaResponse)
                .toList();
    }

    @Override
    @Transactional
    public void optimizeAndSaveRoute(Entregador entregador, Origem origem, List<PedidoResponse> pedidosPendentes) {
        saveRouteAndStops(
                openRouteService.getOptimizeRoute(pedidosPendentes, entregador, origem),
                entregador,
                origem
        );
    }

    @Override
    public Rota findEntityById(Long rotaId) {
        return repository.findById(rotaId)
                .orElseThrow(() -> new ResourceNotFoundException("Rota não encontrada pelo id: " + rotaId));
    }

    @Override
    public Rota findEntityByPedidoId(Long pedidoId) {
        return repository.findByPedidoId(pedidoId)
                .orElseThrow(() -> new ResourceNotFoundException("Rota não encontrada pelo pedidoId: " + pedidoId));
    }

    @Override
    public List<Rota> findEntitiesByEntregadorId(Long entregadorId) {
        return getByEntregadorId(entregadorId);
    }

    @Override
    public List<Rota> findByStatus(RotaStatusEnum status) {
        return repository.findAllByStatus(status);
    }

    @Override
    public void finalizarRota(Rota rota) {
        rota.setStatus(RotaStatusEnum.FINALIZADA);
        entregadorService.liberarEntregador(rota.getEntregador());
        repository.save(rota);
    }

    private void saveRouteAndStops(OptimizeResponse optimizeResponse, Entregador entregador, Origem origem) {
        repository.save(mapToRota(optimizeResponse, entregador, origem));
    }

    private Rota mapToRota(OptimizeResponse optimizeResponse, Entregador entregador, Origem origem) {
        return mapper.toRota(optimizeResponse.getRoutes().getFirst(), entregador, origem);
    }

    private List<Rota> getByEntregadorId(Long entregadorId) {
        return repository.findAllByEntregadorId(entregadorId)
                .filter(rotas -> !rotas.isEmpty())
                .orElseThrow(() -> new ResourceNotFoundException("Rotas não encontradas pelo entregadorId"));
    }

}
