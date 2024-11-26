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

class ParadaMapperTest {

    private final ParadaMapper paradaMapper = MapperUtils.paradaMapper();

    @Nested
    class ToResponse {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParada();

            // When
            var response = paradaMapper.toResponse(parada);

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
    }
    @Nested
    class UpdateStatus {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParada();
            var request = new ParadaStatusRequest(PedidoStatusEnum.ENTREGUE);

            // When
            paradaMapper.updateStatus(parada, request);

            // Then
            assertEquals(request.status(), parada.getStatus());
        }
    }

}