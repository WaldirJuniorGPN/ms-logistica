package com.techchallenge4.ms_logistica.api.v1.response;

import com.techchallenge4.ms_logistica.enums.RegiaoEnum;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import lombok.Builder;

@Builder
public record OrigemResponse(
        Long id,
        String nome,
        String endereco,
        Double latitude,
        Double longitude,
        String cep,
        EstadoEnum estado,
        RegiaoEnum regiao
) {
}
