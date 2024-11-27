package com.techchallenge4.ms_logistica.integration.repository;

import com.techchallenge4.ms_logistica.domain.Rota;
import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.RastreamentoRepository;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.utils.RastreamentoUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest
class RastreamentoRepositoryIT extends AbstractContainerIT {

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private RastreamentoRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class Save {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var rastreamento = RastreamentoUtil.buildRastreamentoSemId();
            rastreamento.setRota(rota);

            // When
            repository.save(rastreamento);
            var rastreamentos = repository.findAll();

            // Then
            assertEquals(1, rastreamentos.size());
            assertEquals(rastreamento, rastreamentos.getFirst());
        }
    }

    @Nested
    class FindAll {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var rastreamento = RastreamentoUtil.buildRastreamentoSemId();
            rastreamento.setRota(rota);

            // When
            rotaRepository.save(rastreamento.getRota());
            repository.save(rastreamento);
            var rastreamentos = repository.findAll();

            // Then
            assertEquals(1, rastreamentos.size());
            assertEquals(rastreamento, rastreamentos.getFirst());
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var rastreamento = RastreamentoUtil.buildRastreamentoSemId();
            rastreamento.setRota(rota);

            // When
            rotaRepository.save(rastreamento.getRota());
            repository.save(rastreamento);
            var rastreamentoSalvo = repository.findById(rastreamento.getId()).orElse(null);

            // Then
            assertEquals(rastreamento, rastreamentoSalvo);
        }
    }

    @Nested
    class FindByRota {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var rastreamento = RastreamentoUtil.buildRastreamentoSemId();
            rastreamento.setRota(rota);

            // When
            rotaRepository.save(rastreamento.getRota());
            repository.save(rastreamento);
            var rastreamentoSalvo = repository.findByRota(rastreamento.getRota());

            // Then
            assertTrue(rastreamentoSalvo.isPresent());
            assertEquals(rastreamento, rastreamentoSalvo.get());
        }
    }

    @Nested
    class Update {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var rastreamento = RastreamentoUtil.buildRastreamentoSemId();
            rastreamento.setRota(rota);

            // When
            rotaRepository.save(rastreamento.getRota());
            repository.save(rastreamento);
            rastreamento.setUltimaLatitude(rastreamento.getUltimaLatitude() + 1);
            repository.save(rastreamento);
            var rastreamentos = repository.findAll();

            // Then
            assertEquals(1, rastreamentos.size());
            assertEquals(rastreamento, rastreamentos.getFirst());
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var rota = rotaRepository.save(new Rota());

            var rastreamento = RastreamentoUtil.buildRastreamentoSemId();
            rastreamento.setRota(rota);

            // When
            rotaRepository.save(rastreamento.getRota());
            repository.save(rastreamento);
            repository.delete(rastreamento);
            var rastreamentos = repository.findAll();

            // Then
            assertEquals(0, rastreamentos.size());
        }
    }
}