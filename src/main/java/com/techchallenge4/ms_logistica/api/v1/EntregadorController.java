package com.techchallenge4.ms_logistica.api.v1;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entregador")
public class EntregadorController {

    private final EntregadorService service;

    @PostMapping
    public ResponseEntity<EntregadorResponse> createEntregador(@RequestBody EntregadorRequest request) {
        return ResponseEntity.ok(service.create(request));
    }

    @GetMapping
    public ResponseEntity<List<EntregadorResponse>> getAllEntregadores() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregadorResponse> getEntregadorById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntregadorResponse> updateEntregador(@PathVariable Long id, @RequestBody EntregadorRequest request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntregador(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
