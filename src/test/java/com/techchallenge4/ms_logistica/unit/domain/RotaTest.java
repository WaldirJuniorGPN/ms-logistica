package com.techchallenge4.ms_logistica.unit.domain;

import com.techchallenge4.ms_logistica.utils.RotaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.techchallenge4.ms_logistica.enums.PedidoStatusEnum.ENTREGUE;
import static com.techchallenge4.ms_logistica.enums.PedidoStatusEnum.PENDENTE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RotaTest {

    @Nested
    class GetParadaByPedidoId {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var rota = RotaUtils.buildRota();
            var pedidoId = rota.getParadas().getFirst().getPedidoId();

            // When
            var result = rota.getParadaByPedidoId(pedidoId);

            // Then
            assertNotNull(result);
            assertTrue(result.isPresent());
            assertEquals(pedidoId, result.get().getPedidoId());
        }
        @Test
        void shouldReturnEmptyWhenPedidoIdNotFound() {
            // Given
            var rota = RotaUtils.buildRota();
            var pedidoId = rota.getParadas().getFirst().getPedidoId() + 100;

            // When
            var result = rota.getParadaByPedidoId(pedidoId);

            // Then
            assertNotNull(result);
            assertTrue(result.isEmpty());
        }
    }

    @Nested
    class IsRotaFinalizada {
        @Test
        void shouldReturnTrueWhenAllParadasEntregues() {
            // Given
            var rota = RotaUtils.buildRota();
            rota.getParadas().forEach(parada -> parada.setStatus(ENTREGUE));

            // When
            var result = rota.isRotaFinalizada();

            // Then
            assertTrue(result);
        }
        @Test
        void shouldReturnFalseWhenAnyParadaNotEntregue() {
            // Given
            var rota = RotaUtils.buildRota();
            rota.getParadas().forEach(parada -> parada.setStatus(PENDENTE));

            // When
            var result = rota.isRotaFinalizada();

            // Then
            assertFalse(result);
        }
    }

}
