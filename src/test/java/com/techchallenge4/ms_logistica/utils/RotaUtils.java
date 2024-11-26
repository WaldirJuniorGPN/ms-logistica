package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.response.RotaResponse;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RotaUtils {

    public static Rota buildRota() {
        return Rota.builder()
                .id(1L)
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

}
