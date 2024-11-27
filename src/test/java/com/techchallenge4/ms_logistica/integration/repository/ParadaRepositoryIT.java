package com.techchallenge4.ms_logistica.integration.repository;

import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.ParadaRepository;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class ParadaRepositoryIT extends AbstractContainerIT {

    @Autowired
    private ParadaRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class Save {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParadaSemId();

            // When
            repository.save(parada);
            var paradas = repository.findAll();

            // Then
            assertEquals(1, paradas.size());
            assertEquals(parada, paradas.getFirst());
        }
    }

    @Nested
    class FindAll {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParadaSemId();

            // When
            repository.save(parada);
            var paradas = repository.findAll();

            // Then
            assertEquals(1, paradas.size());
            assertEquals(parada, paradas.getFirst());
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParadaSemId();

            // When
            repository.save(parada);
            var paradaSalva = repository.findById(parada.getId()).orElse(null);

            // Then
            assertEquals(parada, paradaSalva);
        }
    }

    @Nested
    class Update {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParadaSemId();

            // When
            repository.save(parada);
            parada.setObservacao("Nova observação");
            repository.save(parada);
            var paradas = repository.findAll();

            // Then
            assertEquals(1, paradas.size());
            assertEquals(parada, paradas.getFirst());
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var parada = ParadaUtils.buildParadaSemId();

            // When
            repository.save(parada);
            repository.delete(parada);
            var paradas = repository.findAll();

            // Then
            assertEquals(0, paradas.size());
        }
    }
}