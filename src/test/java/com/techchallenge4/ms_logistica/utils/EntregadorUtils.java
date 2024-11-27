package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.IntStream;

@NoArgsConstructor
public class EntregadorUtils {

    public static EntregadorResponse buildEntregadorResponse() {
        return EntregadorResponse.builder()
                .id(1L)
                .nome("nome")
                .cpf("cpf")
                .contato("contato")
                .capacidade(100)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .disponivel(true)
                .build();
    }

    public static EntregadorRequest buildEntregadorRequest() {
        return EntregadorRequest.builder()
                .nome("nome")
                .cpf("cpf")
                .contato("contato")
                .capacidade(100)
                .cep("02435-060")
                .build();
    }

    public static List<Entregador> buildEntregadorList(int numberOfMocks) {
        return IntStream.range(0, numberOfMocks)
                .mapToObj(i -> buildEntregador((long) i))
                .toList();
    }

    public static Entregador buildEntregador(Long id) {
        return Entregador.builder()
                .id(id)
                .nome("nome")
                .cpf("cpf")
                .contato("contato")
                .capacidade(100)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .disponivel(true)
                .build();
    }

    public static Entregador buildEntregador() {
        return Entregador.builder()
                .id(1L)
                .nome("nome")
                .cpf("cpf")
                .contato("contato")
                .capacidade(100)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .disponivel(true)
                .build();
    }

    public static Entregador buildEntregadorSemId() {
        return Entregador.builder()
                .nome("nome")
                .cpf("cpf")
                .contato("contato")
                .capacidade(100)
                .cep("02435-060")
                .estado(EstadoEnum.SP)
                .disponivel(true)
                .build();
    }

}
