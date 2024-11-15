package com.techchallenge4.ms_logistica.api.v1.response;

import java.util.List;

public record RotaResponse(
        Long id,
        OrigemResponse origem,
        List<ParadaResponse> paradas
) {
}
