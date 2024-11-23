package com.techchallenge4.ms_logistica.service.v1.impl;

import com.techchallenge4.ms_logistica.api.v1.request.ParadaStatusRequest;
import com.techchallenge4.ms_logistica.api.v1.response.ParadaResponse;
import com.techchallenge4.ms_logistica.client.PedidoServiceClient;
import com.techchallenge4.ms_logistica.domain.Parada;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.ParadaMapper;
import com.techchallenge4.ms_logistica.repository.ParadaRepository;
import com.techchallenge4.ms_logistica.service.v1.ParadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParadaServiceImpl implements ParadaService {

    private final ParadaRepository repository;
    private final ParadaMapper mapper;
    private final PedidoServiceClient pedidoServiceClient;

    @Override
    public ParadaResponse patchStatus(Long id, ParadaStatusRequest request) {
        return repository.findById(id)
                .map(parada -> updateAndSaveStatus(request, parada))
                .orElseThrow(() -> new ResourceNotFoundException("Parada não encontrada pelo id: " + id));

    }

    private ParadaResponse updateAndSaveStatus(ParadaStatusRequest request, Parada parada) {
        pedidoServiceClient.updatePedidoStatus(parada.getPedidoId(), request.status());
        mapper.updateStatus(parada, request);
        return mapper.toResponse(repository.save(parada));
    }

}
