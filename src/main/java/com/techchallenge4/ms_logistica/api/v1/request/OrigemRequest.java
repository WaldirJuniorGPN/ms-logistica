package com.techchallenge4.ms_logistica.api.v1.request;

import lombok.Builder;

@Builder
public record OrigemRequest(
        String nome,
        String endereco,
        Double latitude,
        Double longitude,
        String cep
) {
}
