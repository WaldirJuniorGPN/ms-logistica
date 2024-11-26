package com.techchallenge4.ms_logistica.client.response;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PedidoResponse(
        Long id,
        Long clienteId,
        Long produtoId,
        Integer quantidade,
        Double latitude,
        Double longitude,
        LocalDateTime dataHoraCriacao,
        PedidoStatusEnum status,
        EstadoEnum estado
) {
}