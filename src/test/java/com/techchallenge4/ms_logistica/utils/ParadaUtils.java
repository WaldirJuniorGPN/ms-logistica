package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.request.ParadaStatusRequest;
import com.techchallenge4.ms_logistica.api.v1.response.ParadaResponse;
import com.techchallenge4.ms_logistica.domain.Parada;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor
public class ParadaUtils {

    public static ParadaStatusRequest buildParadaStatusRequest() {
        return ParadaStatusRequest.builder()
                .status(PedidoStatusEnum.PENDENTE)
                .build();
    }

    public static List<ParadaResponse> buildParadaResponseList(int numberOfMocks) {
        return IntStream.range(0, numberOfMocks)
                .mapToObj(i -> buildParadaResponse((long) i))
                .toList();
    }

    public static ParadaResponse buildParadaResponse() {
        return buildParadaResponse(1L);
    }

    public static ParadaResponse buildParadaResponse(Long id) {
        return ParadaResponse.builder()
                .id(id)
                .pedidoId(id)
                .sequencia(id)
                .latitude(8.686507)
                .longitude(49.41943)
                .contato("contato")
                .status(PedidoStatusEnum.PENDENTE)
                .observacao("observacao")
                .build();
    }

    public static List<Parada> buildParadaList(int numberOfMocks) {
        return IntStream.range(0, numberOfMocks)
                .mapToObj(i -> buildParada((long) i))
                .toList();
    }

    public static Parada buildParada() {
        return buildParada(1L);
    }

    public static Parada buildParada(Long id) {
        return Parada.builder()
                .id(id)
                .pedidoId(id)
                .sequencia(id)
                .latitude(8.686507)
                .longitude(49.41943)
                .contato("contato")
                .status(PedidoStatusEnum.PENDENTE)
                .observacao("observacao")
                .build();
    }

    public static Parada buildParadaSemId() {
        return Parada.builder()
                .pedidoId(1L)
                .sequencia(1L)
                .latitude(8.686507)
                .longitude(49.41943)
                .contato("contato")
                .status(PedidoStatusEnum.PENDENTE)
                .observacao("observacao")
                .build();
    }
}
