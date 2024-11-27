package com.techchallenge4.ms_logistica.integration.repository;

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
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
class RotaRepositoryIT extends AbstractContainerIT {

    @Autowired
    private OrigemRepository origemRepository;

    @Autowired
    private EntregadorRepository entregadorRepository;

    @Autowired
    private RotaRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class Save {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            var rotas = repository.findAll();

            // Then
            assertEquals(1, rotas.size());
            assertEquals(rota, rotas.getFirst());
        }
    }

    @Nested
    class FindAll {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            var rotas = repository.findAll();

            // Then
            assertEquals(1, rotas.size());
            assertEquals(rota, rotas.getFirst());
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            var rotaSalva = repository.findById(rota.getId()).orElse(null);

            // Then
            assertEquals(rota, rotaSalva);
        }
    }

    @Nested
    class FindByPedidoId {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            var rotaSalva = repository.findByPedidoId(rota.getParadas().getFirst().getPedidoId());

            // Then
            assertTrue(rotaSalva.isPresent());
            assertEquals(rota, rotaSalva.get());
        }
    }

    @Nested
    class FindAllByEntregadorId {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            var rotas = repository.findAllByEntregadorId(rota.getEntregador().getId());

            // Then
            assertTrue(rotas.isPresent());
            assertEquals(1, rotas.get().size());
            assertEquals(rota, rotas.get().getFirst());
        }
    }

    @Nested
    class FindAllByStatus {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            var rotas = repository.findAllByStatus(rota.getStatus());

            // Then
            assertEquals(1, rotas.size());
            assertEquals(rota, rotas.getFirst());
        }
    }

    @Nested
    class Update {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            rota.setStatus(RotaStatusEnum.PENDENTE);
            repository.save(rota);
            var rotas = repository.findAll();

            // Then
            assertEquals(1, rotas.size());
            assertEquals(rota, rotas.getFirst());
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = buildRotaWithRelatedEntities();

            // When
            repository.save(rota);
            repository.delete(rota);
            var rotas = repository.findAll();

            // Then
            assertEquals(0, rotas.size());
        }
    }

    private Rota buildRotaWithRelatedEntities() {
        return Rota.builder()
                .status(RotaStatusEnum.PENDENTE)
                .origem(origemRepository.save(OrigemUtils.buildOrigemSemId()))
                .entregador(entregadorRepository.save(EntregadorUtils.buildEntregadorSemId()))
                .paradas(List.of(ParadaUtils.buildParadaSemId()))
                .build();
    }

}