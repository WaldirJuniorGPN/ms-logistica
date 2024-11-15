package com.techchallenge4.ms_logistica.client;

import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.enums.CepEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@FeignClient(value = "pedido-service", url = "${services.pedido-service.url}")
public interface PedidoServiceClient {

//    @GetMapping("/v1/pedidos")
//    List<PedidoResponse> getPedidos(@RequestParam CepEnum cep, @RequestParam PedidoStatus status);

    default List<PedidoResponse> getPedidos(CepEnum cep, PedidoStatusEnum status) {
        List<PedidoResponse> pedidos = new ArrayList<>();
        var totalPedidos = 25;

        for (int i = 1; i <= totalPedidos; i++) {
            pedidos.add(PedidoResponse.builder()
                    .id((long) i)
                    .clienteId(100L + i)
                    .produtoId(1000L + i)
                    .quantidade((i % 5) + 1)
                    .dataHoraCriacao(LocalDateTime.now().minusHours(1))
                    .status(status)
                    .cep(cep)
                    .build());
        }
        return pedidos;
    }

}
