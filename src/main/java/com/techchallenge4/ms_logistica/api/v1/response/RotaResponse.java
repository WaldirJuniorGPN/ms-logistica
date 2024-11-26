package com.techchallenge4.ms_logistica.api.v1.response;

import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import lombok.Builder;

import java.util.List;

@Builder
public record RotaResponse(
        Long id,
        RotaStatusEnum status,
        OrigemResponse origem,
        EntregadorResponse entregador,
        List<ParadaResponse> paradas
) {
}
