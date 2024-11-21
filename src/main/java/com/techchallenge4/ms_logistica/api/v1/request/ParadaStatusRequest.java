package com.techchallenge4.ms_logistica.api.v1.request;

import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;

public record ParadaStatusRequest(
        PedidoStatusEnum status
) {
}
