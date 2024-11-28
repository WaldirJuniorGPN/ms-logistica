package com.techchallenge4.ms_logistica.integration.api.v1;

import com.techchallenge4.ms_logistica.api.v1.EntregadorController;
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
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
class EntregadorControllerIT extends AbstractContainerIT {

    @Autowired
    private EntregadorRepository repository;

    @Autowired
    private EntregadorController controller;

    @BeforeEach
    void setUp() {
        repository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class CreateEntregador {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var request = EntregadorUtils.buildEntregadorRequest();

            // When
            var response = controller.createEntregador(request);

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(request.nome(), response.getBody().nome());
        }
    }

    @Nested
    class GetAllEntregadores {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            repository.save(EntregadorUtils.buildEntregadorSemId());

            // When
            var response = controller.getAllEntregadores();

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(1, response.getBody().size());
        }
    }

    @Nested
    class GetEntregadorById {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = repository.save(EntregadorUtils.buildEntregadorSemId());

            // When
            var response = controller.getEntregadorById(entregador.getId());

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(entregador.getId(), response.getBody().id());
        }
    }

    @Nested
    class UpdateEntregador {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = repository.save(EntregadorUtils.buildEntregadorSemId());
            var request = EntregadorUtils.buildEntregadorRequest();

            // When
            var response = controller.updateEntregador(entregador.getId(), request);

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(request.nome(), response.getBody().nome());
        }
    }

    @Nested
    class DeleteEntregador {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var entregador = repository.save(EntregadorUtils.buildEntregador());

            // When
            var response = controller.deleteEntregador(entregador.getId());

            // Then
            assertNotNull(response);
            assertEquals(204, response.getStatusCode().value());
        }
    }

}
