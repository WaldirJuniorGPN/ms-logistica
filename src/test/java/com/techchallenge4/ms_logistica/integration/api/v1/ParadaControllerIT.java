package com.techchallenge4.ms_logistica.integration.api.v1;

import com.techchallenge4.ms_logistica.api.v1.ParadaController;
import com.techchallenge4.ms_logistica.client.PedidoClient;
import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.ParadaRepository;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Testcontainers
@SpringBootTest
class ParadaControllerIT extends AbstractContainerIT {

    @Autowired
    private ParadaRepository repository;

    @Autowired
    private ParadaController controller;

    @MockBean
    private PedidoClient pedidoClient;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class PatchParadaStatus {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var request = ParadaUtils.buildParadaStatusRequest().withStatus(PedidoStatusEnum.ENTREGUE);
            var parada = ParadaUtils.buildParadaSemId();
            repository.save(parada);

            when(pedidoClient.updatePedidoStatus(any(Long.class), any(PedidoStatusEnum.class))).thenReturn(null);

            // When
            var response = controller.patchParadaStatus(parada.getId(), request);

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(request.status(), response.getBody().status());
        }
    }
}