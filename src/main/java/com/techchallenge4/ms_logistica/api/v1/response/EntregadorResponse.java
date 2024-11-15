package com.techchallenge4.ms_logistica.api.v1.response;

import lombok.Builder;

@Builder
public record EntregadorResponse(
    Long id,
    String nome,
    String cpf,
    String contato
) {
}
