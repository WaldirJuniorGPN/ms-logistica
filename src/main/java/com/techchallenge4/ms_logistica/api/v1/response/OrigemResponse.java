package com.techchallenge4.ms_logistica.api.v1.response;

import lombok.Builder;

@Builder
public record OrigemResponse(
        Long id,
        String nome,
        String endereco,
        Double latitude,
        Double longitude
) {
}
