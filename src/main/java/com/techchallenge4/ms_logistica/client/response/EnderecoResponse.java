package com.techchallenge4.ms_logistica.client.response;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import lombok.Builder;

@Builder
public record EnderecoResponse(
        String cep,
        String logradouro,
        String numero,
        String complemento,
        String bairro,
        String cidade,
        Double latitude,
        Double longitude,
        EstadoEnum uf
) {
}
