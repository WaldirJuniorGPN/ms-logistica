package com.techchallenge4.ms_logistica.client;

import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "pedido-service", url = "${services.pedido-service.url}")
public interface PedidoServiceClient {

    @GetMapping("/pedidos")
    List<PedidoResponse> getPedidosByEstadoEStatus(@RequestParam EstadoEnum estado, @RequestParam PedidoStatusEnum status);

    @PatchMapping("/pedidos/{id}/status/{status}")
    PedidoResponse updatePedidoStatus(@PathVariable Long id, @PathVariable PedidoStatusEnum status);

}
