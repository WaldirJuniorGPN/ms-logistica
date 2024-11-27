package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.request.OrigemRequest;
import com.techchallenge4.ms_logistica.api.v1.response.OrigemResponse;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.RegiaoEnum;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor
public class OrigemUtils {

    public static OrigemResponse buildOrigemResponse() {
        return OrigemResponse.builder()
                .id(1L)
                .nome("nome")
                .endereco("endereco")
                .latitude(1.0)
                .longitude(1.0)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .regiao(RegiaoEnum.SUDESTE)
                .build();
    }

    public static OrigemRequest buildOrigemRequest() {
        return OrigemRequest.builder()
                .nome("nome")
                .endereco("endereco")
                .latitude(1.0)
                .longitude(1.0)
                .cep("02435-060")
                .build();
    }

    public static List<Origem> buildOrigemList(int numberOfMocks) {
        return IntStream.range(0, numberOfMocks)
                .mapToObj(i -> buildOrigem((long) i))
                .toList();
    }

    public static Origem buildOrigem(Long id) {
        return Origem.builder()
                .id(id)
                .nome("nome")
                .endereco("endereco")
                .latitude(1.0)
                .longitude(1.0)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .regiao(RegiaoEnum.SUDESTE)
                .build();
    }

    public static Origem buildOrigem() {
        return Origem.builder()
                .id(1L)
                .nome("nome")
                .endereco("endereco")
                .latitude(1.0)
                .longitude(1.0)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .regiao(RegiaoEnum.SUDESTE)
                .build();
    }

    public static Origem buildOrigemSemId() {
        return Origem.builder()
                .nome("nome")
                .endereco("endereco")
                .latitude(1.0)
                .longitude(1.0)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .regiao(RegiaoEnum.SUDESTE)
                .build();
    }

}
