package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor
public class PedidoUtils {

    public static List<PedidoResponse> buildPedidoResponseList(int numberOfMocks) {
        return IntStream.range(0, numberOfMocks)
                .mapToObj(i -> buildPedidoResponse((long) i))
                .toList();
    }

    public static PedidoResponse buildPedidoResponse() {
        return buildPedidoResponse(1L);
    }

    public static PedidoResponse buildPedidoResponse(Long id) {
        return PedidoResponse.builder()
                .id(id)
                .clienteId(id + 100)
                .produtoId(id + 200)
                .quantidade(1)
                .latitude(10.0 + id)
                .longitude(20.0 + id)
                .dataHoraCriacao(LocalDateTime.now())
                .status(PedidoStatusEnum.PENDENTE)
                .estado(EstadoEnum.SP)
                .build();
    }
}