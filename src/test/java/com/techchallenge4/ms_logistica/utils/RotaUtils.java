package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.response.RotaResponse;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor
public class RotaUtils {

    public static List<Rota> buildRotaList(int numberOfMocks) {
        return IntStream.range(0, numberOfMocks)
                .mapToObj(i -> buildRota((long) i))
                .toList();
    }

    public static Rota buildRota() {
        return buildRota(1L);
    }

    public static Rota buildRota(Long id) {
        return Rota.builder()
                .id(id)
                .status(RotaStatusEnum.EM_ANDAMENTO)
                .origem(OrigemUtils.buildOrigem())
                .entregador(EntregadorUtils.buildEntregador())
                .paradas(ParadaUtils.buildParadaList(3))
                .build();
    }

    public static RotaResponse buildRotaResponse() {
        return RotaResponse.builder()
                .id(1L)
                .status(RotaStatusEnum.EM_ANDAMENTO)
                .origem(OrigemUtils.buildOrigemResponse())
                .entregador(EntregadorUtils.buildEntregadorResponse())
                .paradas(ParadaUtils.buildParadaResponseList(3))
                .build();
    }

    public static Rota buildRotaSemId() {
        return Rota.builder()
                .status(RotaStatusEnum.EM_ANDAMENTO)
                .origem(OrigemUtils.buildOrigem())
                .entregador(EntregadorUtils.buildEntregador())
                .paradas(ParadaUtils.buildParadaList(3))
                .build();
    }
}
