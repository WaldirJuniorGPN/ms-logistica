package com.techchallenge4.ms_logistica.api.v1.request;

import lombok.Builder;

@Builder
public record EntregadorRequest(
    String nome,
    String cpf,
    String contato
) {
}
