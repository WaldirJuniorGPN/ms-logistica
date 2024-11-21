package com.techchallenge4.ms_logistica.api.v1.request;

public record RastreamentoRequest(
        Long rotaId,
        Double latitude,
        Double longitude
) {
}
