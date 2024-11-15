package com.techchallenge4.ms_logistica.api.v1.response;

import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;

public record ParadaResponse(
        Long id,
        String pedidoId,
        Long sequencia  ,
        Double latitude,
        Double longitude,
        String contato,
        PedidoStatusEnum status,
        String observacao
) {
}
