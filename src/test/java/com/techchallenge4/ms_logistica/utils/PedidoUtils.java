package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.client.response.EnderecoResponse;
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
                .endereco(buildEnderecoResponse())
                .dataHoraCriacao(LocalDateTime.now())
                .status(PedidoStatusEnum.PENDENTE)
                .uf(EstadoEnum.SP)
                .build();
    }

    public static EnderecoResponse buildEnderecoResponse() {
        return EnderecoResponse.builder()
                .cep("12345678")
                .logradouro("Rua dos Bobos")
                .numero("0")
                .complemento("Casa")
                .bairro("Vila Esperança")
                .cidade("São Paulo")
                .latitude(8.686507)
                .longitude(49.41943)
                .build();
    }
}