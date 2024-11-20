package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.service.v1.DirectionsService;
import com.techchallenge4.ms_logistica.service.v1.OpenRouteService;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectionsServiceImpl implements DirectionsService {

    private final RotaService rotaService;
    private final OpenRouteService openRouteService;

    @Override
    public List<DirectionsResponse> findDirectionsByEntregadorId(Long entregadorId) {
        return rotaService.findEntitiesByEntregadorId(entregadorId).stream()
                .map(openRouteService::getDirections)
                .toList();
    }

}
