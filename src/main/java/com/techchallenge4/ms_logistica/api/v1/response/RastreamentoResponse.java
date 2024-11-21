package com.techchallenge4.ms_logistica.api.v1.response;

public record RastreamentoResponse(
        Long id,
        String status,
        String origem,
        String entregador,
        String parada
) {
}
