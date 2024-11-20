package com.techchallenge4.ms_logistica.api.v1.response;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import lombok.Builder;

@Builder
public record EntregadorResponse(
        Long id,
        String nome,
        String cpf,
        String contato,
        Integer capacidade,
        String cep,
        EstadoEnum estado,
        Boolean disponivel
) {
}
