package com.techchallenge4.ms_logistica.unit.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.ParadaStatusRequest;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import com.techchallenge4.ms_logistica.mapper.ParadaMapper;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class ParadaMapperTest {

    private final ParadaMapper mapper = MapperUtils.paradaMapper();

    @Nested
    class ToResponse {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParada();

            // When
            var response = mapper.toResponse(parada);

            // Then
            assertNotNull(response);
            assertEquals(parada.getId(), response.id());
            assertEquals(parada.getPedidoId(), response.pedidoId());
            assertEquals(parada.getSequencia(), response.sequencia());
            assertEquals(parada.getLatitude(), response.latitude());
            assertEquals(parada.getLongitude(), response.longitude());
            assertEquals(parada.getContato(), response.contato());
            assertEquals(parada.getStatus(), response.status());
            assertEquals(parada.getObservacao(), response.observacao());
        }
        @Test
        void shouldReturnNullWhenParadaIsNull() {
            // When
            var response = mapper.toResponse(null);

            // Then
            assertNull(response);
        }
    }
    @Nested
    class UpdateStatus {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParada();
            var request = new ParadaStatusRequest(PedidoStatusEnum.ENTREGUE);

            // When
            mapper.updateStatus(parada, request);

            // Then
            assertEquals(request.status(), parada.getStatus());
        }
        @Test
        void shouldReturnSuccessfullyWhenRequestIsNull() {
            // Given
            var parada = ParadaUtils.buildParada();
            var initialStatus = parada.getStatus();

            // When
            mapper.updateStatus(parada, null);

            // Then
            assertEquals(initialStatus, parada.getStatus());
        }
    }

}