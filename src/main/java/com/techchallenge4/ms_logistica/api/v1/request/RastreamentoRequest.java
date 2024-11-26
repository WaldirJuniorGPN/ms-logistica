package com.techchallenge4.ms_logistica.api.v1.request;

import lombok.Builder;
import lombok.With;

@With
@Builder
public record RastreamentoRequest(
        Long rotaId,
        Double latitude,
        Double longitude
) {
}
