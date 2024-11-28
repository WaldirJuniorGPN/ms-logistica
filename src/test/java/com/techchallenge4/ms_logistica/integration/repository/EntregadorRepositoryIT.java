package com.techchallenge4.ms_logistica.integration.repository;

import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.EntregadorRepository;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest
class EntregadorRepositoryIT extends AbstractContainerIT {

    @Autowired
    private EntregadorRepository repository;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class Save {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregadorSemId();

            // When
            repository.save(entregador);
            var entregadores = repository.findAll();

            // Then
            assertEquals(1, entregadores.size());
            assertEquals(entregador, entregadores.getFirst());
        }
    }

    @Nested
    class FindAll {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregadorSemId();

            // When
            repository.save(entregador);
            var entregadores = repository.findAll();

            // Then
            assertEquals(1, entregadores.size());
            assertEquals(entregador, entregadores.getFirst());
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregadorSemId();

            // When
            repository.save(entregador);
            var entregadorSalvo = repository.findById(entregador.getId()).orElse(null);

            // Then
            assertEquals(entregador, entregadorSalvo);
        }
    }

    @Nested
    class FindByEstadoAndDisponivel {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregadorSemId();

            // When
            repository.save(entregador);
            var entregadores = repository.findByEstadoAndDisponivel(entregador.getEstado(), entregador.getDisponivel());

            // Then
            assertEquals(1, entregadores.size());
            assertEquals(entregador, entregadores.getFirst());
        }
    }

    @Nested
    class Update {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregadorSemId();

            // When
            repository.save(entregador);
            entregador.setNome("Novo nome");
            repository.save(entregador);
            var entregadores = repository.findAll();

            // Then
            assertEquals(1, entregadores.size());
            assertEquals(entregador, entregadores.getFirst());
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregadorSemId();

            // When
            repository.save(entregador);
            repository.delete(entregador);
            var entregadores = repository.findAll();

            // Then
            assertEquals(0, entregadores.size());
        }
    }

}
