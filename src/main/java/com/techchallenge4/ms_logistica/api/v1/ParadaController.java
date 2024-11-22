package com.techchallenge4.ms_logistica.api.v1;

import com.techchallenge4.ms_logistica.api.v1.request.ParadaStatusRequest;
import com.techchallenge4.ms_logistica.api.v1.response.ParadaResponse;
import com.techchallenge4.ms_logistica.service.v1.ParadaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parada")
public class ParadaController {

    private final ParadaService service;

    @PatchMapping("/{id}/status")
    public ResponseEntity<ParadaResponse> patchParadaStatus(@PathVariable Long id, @RequestBody ParadaStatusRequest request) {
        return ResponseEntity.ok(service.patchStatus(id, request));
    }

}
