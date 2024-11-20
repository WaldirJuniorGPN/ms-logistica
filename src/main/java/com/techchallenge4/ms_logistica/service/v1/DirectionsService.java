package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;

import java.util.List;

public interface DirectionsService {

    List<DirectionsResponse> findDirectionsByEntregadorId(Long entregadorId);

}
