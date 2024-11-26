package com.techchallenge4.ms_logistica.api.v1.response;

import lombok.Builder;

@Builder
public record RastreamentoResponse(
        Long id,
        Long rotaId,
        Double ultimaLatitude,
        Double ultimaLongitude
) {
}
