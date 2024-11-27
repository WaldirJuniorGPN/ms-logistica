package com.techchallenge4.ms_logistica.integration.api.v1;

import com.techchallenge4.ms_logistica.api.v1.RotaController;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.EntregadorRepository;
import com.techchallenge4.ms_logistica.repository.OrigemRepository;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
class RotaControllerIT extends AbstractContainerIT {

    @Autowired
    private OrigemRepository origemRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private RotaController controller;

    @AfterEach
    void tearDown() {
        rotaRepository.deleteAll();
        origemRepository.deleteAll();
        entregadorRepository.deleteAll();
    }

    @Nested
    class GetRotaByPedidoId {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = origemRepository.save(OrigemUtils.buildOrigemSemId());
            var entregador = entregadorRepository.save(EntregadorUtils.buildEntregadorSemId());
            var rota = rotaRepository.save(Rota.builder()
                    .status(RotaStatusEnum.EM_ANDAMENTO)
                    .origem(origem)
                    .entregador(entregador)
                    .paradas(List.of(ParadaUtils.buildParadaSemId()))
                    .build()
            );

            // When
            var response = controller.getRotaByPedidoId(rota.getParadas().getFirst().getPedidoId());

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(rota.getId(), response.getBody().id());
        }
    }

    @Nested
    class GetRotaByEntregadorId {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = origemRepository.save(OrigemUtils.buildOrigemSemId());
            var entregador = entregadorRepository.save(EntregadorUtils.buildEntregadorSemId());
            var rota = rotaRepository.save(Rota.builder()
                    .status(RotaStatusEnum.EM_ANDAMENTO)
                    .origem(origem)
                    .entregador(entregador)
                    .paradas(List.of(ParadaUtils.buildParadaSemId()))
                    .build()
            );

            // When
            var response = controller.getRotaByEntregadorId(entregador.getId());

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(1, response.getBody().size());
            assertEquals(rota.getId(), response.getBody().getFirst().id());
        }
    }

}