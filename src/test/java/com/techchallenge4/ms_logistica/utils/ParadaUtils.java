package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.response.ParadaResponse;
import com.techchallenge4.ms_logistica.domain.Parada;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ParadaUtils {

    public static List<ParadaResponse> buildParadaResponseList(int numberOfMocks) {
        var paradas = new ArrayList<ParadaResponse>();
        for (int i = 0; i < numberOfMocks; i++) {
            paradas.add(buildParadaResponse((long) i));
        }
        return paradas;
    }

    public static ParadaResponse buildParadaResponse(Long id) {
        return ParadaResponse.builder()
                .id(id)
                .pedidoId(id)
                .sequencia(id)
                .latitude(1.0)
                .longitude(1.0)
                .contato("contato")
                .status(PedidoStatusEnum.PENDENTE)
                .observacao("observacao")
                .build();
    }

    public static List<Parada> buildParadaList(int numberOfMocks) {
        var paradas = new ArrayList<Parada>();
        for (int i = 0; i < numberOfMocks; i++) {
            paradas.add(buildParada((long) i));
        }
        return paradas;
    }

    public static Parada buildParada() {
        return buildParada(1L);
    }

    public static Parada buildParada(Long id) {
        return Parada.builder()
                .id(id)
                .pedidoId(id)
                .sequencia(id)
                .latitude(1.0)
                .longitude(1.0)
                .contato("contato")
                .status(PedidoStatusEnum.PENDENTE)
                .observacao("observacao")
                .build();
    }

}
