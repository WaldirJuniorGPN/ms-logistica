package com.techchallenge4.ms_logistica.unit.client.fallback;

import com.techchallenge4.ms_logistica.client.fallback.PedidoClientFallback;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PedidoClientFallbackTest {

    private final PedidoClientFallback fallback = new PedidoClientFallback();

    @Nested
    class GetPedidosByEstadoEStatus {
        @Test
        void getPedidosByEstadoEStatusFallback() {
            // Given
            var response = fallback.getPedidosByEstadoEStatus(null, null);

            // When & Then
            assertEquals(Collections.emptyList(), response);
        }
    }

    @Nested
    class UpdatePedidoStatus {
        @Test
        void updatePedidoStatusFallback() {
            // Given
            var response = fallback.updatePedidoStatus(null, null);

            // When & Then
            assertNull(response);
        }
    }

}
