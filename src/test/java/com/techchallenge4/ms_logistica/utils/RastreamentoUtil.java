package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.request.RastreamentoRequest;
import com.techchallenge4.ms_logistica.api.v1.response.RastreamentoResponse;
import com.techchallenge4.ms_logistica.domain.Rastreamento;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RastreamentoUtil {

    public static Rastreamento buildRastreamento() {
        return Rastreamento.builder()
                .id(1L)
                .rota(RotaUtils.buildRota())
                .ultimaLatitude(8.681495)
                .ultimaLongitude(49.41461)
                .build();
    }

    public static RastreamentoRequest buildRastreamentoRequest() {
        return RastreamentoRequest.builder()
                .rotaId(1L)
                .latitude(8.681495)
                .longitude(49.41461)
                .build();
    }

    public static RastreamentoResponse buildRastreamentoResponse() {
        return RastreamentoResponse.builder()
                .id(1L)
                .rotaId(1L)
                .ultimaLatitude(8.681495)
                .ultimaLongitude(49.41461)
                .build();
    }

    public static Rastreamento buildRastreamentoSemId() {
        return Rastreamento.builder()
                .rota(RotaUtils.buildRotaSemId())
                .ultimaLatitude(8.681495)
                .ultimaLongitude(49.41461)
                .build();
    }
}
