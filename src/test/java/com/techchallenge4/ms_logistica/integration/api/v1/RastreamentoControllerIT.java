package com.techchallenge4.ms_logistica.integration.api.v1;

import com.techchallenge4.ms_logistica.api.v1.RastreamentoController;
import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.EntregadorRepository;
import com.techchallenge4.ms_logistica.repository.OrigemRepository;
import com.techchallenge4.ms_logistica.repository.RastreamentoRepository;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtil;
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
class RastreamentoControllerIT extends AbstractContainerIT {

    @Autowired
    private OrigemRepository origemRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private RastreamentoRepository repository;

    @Autowired
    private RastreamentoController controller;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
        rotaRepository.deleteAll();
        origemRepository.deleteAll();
        entregadorRepository.deleteAll();
    }

    @Nested
    class CreateOrUpdateRastreamento {
        @Test
        void shouldCreateSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var request = RastreamentoUtil.buildRastreamentoRequest().withRotaId(rota.getId());

            // When
            var response = controller.createOrUpdateRastreamento(request);

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(rota.getId(), response.getBody().rotaId());
        }
        @Test
        void shouldUpdateSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var rastreamento = RastreamentoUtil.buildRastreamento();
            rastreamento.setRota(rota);
            repository.save(rastreamento);

            var request = RastreamentoUtil.buildRastreamentoRequest().withRotaId(rota.getId());

            // When
            var response = controller.createOrUpdateRastreamento(request);

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(request.rotaId(), response.getBody().rotaId());
        }
    }

    @Nested
    class GetDirectionsByEntregadorId {
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

            var rastreamento = RastreamentoUtil.buildRastreamento();
            rastreamento.setRota(rota);
            repository.save(rastreamento);

            // When
            var response = controller.getDirectionsByEntregadorId(rota.getEntregador().getId());

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
        }
    }

    @Nested
    class GetDirectionsByPedidoId {
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

            var rastreamento = RastreamentoUtil.buildRastreamento();
            rastreamento.setRota(rota);
            repository.save(rastreamento);

            // When
            var response = controller.getDirectionsByPedidoId(rota.getParadas().getFirst().getPedidoId());

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
        }
    }

}