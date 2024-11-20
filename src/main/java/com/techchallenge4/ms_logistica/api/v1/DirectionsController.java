package com.techchallenge4.ms_logistica.api.v1;

import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.service.v1.DirectionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/directions")
public class DirectionsController {

    private final DirectionsService service;

    @GetMapping("/entregador/{entregadorId}")
    public ResponseEntity<List<DirectionsResponse>> getDirectionsByEntregadorId(@PathVariable Long entregadorId) {
        return ResponseEntity.ok(service.findDirectionsByEntregadorId(entregadorId));
    }

}
