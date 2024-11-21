package com.techchallenge4.ms_logistica.api.v1;

import com.techchallenge4.ms_logistica.api.v1.request.RastreamentoRequest;
import com.techchallenge4.ms_logistica.api.v1.response.RastreamentoResponse;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.service.v1.RastreamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/rastreamento")
public class RastreamentoController {

    private final RastreamentoService service;

    @PostMapping
    public ResponseEntity<RastreamentoResponse> createOrUpdateRastreamento(@RequestBody RastreamentoRequest request) {
        return ResponseEntity.ok(service.createOrUpdateRastreamento(request));
    }

    @GetMapping("/entregador/{entregadorId}")
    public ResponseEntity<List<DirectionsResponse>> getDirectionsByEntregadorId(@PathVariable Long entregadorId) {
        return ResponseEntity.ok(service.findDirectionsByEntregadorId(entregadorId));
    }

    @GetMapping("/pedido/{pedidoId}")
    public ResponseEntity<DirectionsResponse> getDirectionsByPedidoId(@PathVariable Long pedidoId) {
        return ResponseEntity.ok(service.findDirectionsByPedidoId(pedidoId));
    }

}
