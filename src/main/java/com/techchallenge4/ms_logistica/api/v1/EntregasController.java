package com.techchallenge4.ms_logistica.api.v1;

import com.techchallenge4.ms_logistica.schedules.EntregasSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/interno/entregas")
public class EntregasController {

    private final EntregasSchedule service;

    @PostMapping
    public ResponseEntity<Void> executeEntregasSchedule() {
        service.processarPedidosPorEstado();
        return ResponseEntity.noContent().build();
    }

}
