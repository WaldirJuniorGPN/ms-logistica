package com.techchallenge4.ms_logistica.api.v1;

import com.techchallenge4.ms_logistica.api.v1.response.RotaResponse;
import com.techchallenge4.ms_logistica.service.v1.RotaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rota")
public class RotaController {

    private final RotaService service;

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<RotaResponse> getRotaByPedidoId(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(service.findByPedidoId(pedidoId));
    }

    @GetMapping("/entregador/{entregadorId}")
    public ResponseEntity<List<RotaResponse>> getRotaByEntregadorId(@PathVariable Long entregadorId) {
        return ResponseEntity.ok(service.findByEntregadorId(entregadorId));
    }

}
