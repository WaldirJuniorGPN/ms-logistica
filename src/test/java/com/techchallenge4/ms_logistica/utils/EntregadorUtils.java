package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class EntregadorUtils {

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
        var list = new ArrayList<Entregador>();
        for (int i = 0; i < numberOfMocks; i++) {
            list.add(buildEntregador((long) i));
        }
        return list;
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

}
