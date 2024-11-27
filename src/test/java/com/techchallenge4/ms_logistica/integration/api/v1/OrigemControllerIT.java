package com.techchallenge4.ms_logistica.integration.api.v1;

import com.techchallenge4.ms_logistica.api.v1.OrigemController;
import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.OrigemRepository;
import com.techchallenge4.ms_logistica.repository.RotaRepository;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Testcontainers
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrigemControllerIT extends AbstractContainerIT {

    @Autowired
    private RotaRepository rotaRepository;

    @Autowired
    private OrigemRepository repository;

    @Autowired
    private OrigemController controller;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class CreateOrigem {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var request = OrigemUtils.buildOrigemRequest();

            // When
            var response = controller.createOrigem(request);

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(request.nome(), response.getBody().nome());
        }
    }

    @Nested
    class GetAllOrigens {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();
            repository.save(origem);

            // When
            var response = controller.getAllOrigens();

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(1, response.getBody().size());
        }
    }

    @Nested
    class GetOrigemById {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();
            repository.save(origem);

            // When
            var response = controller.getOrigemById(origem.getId());

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(origem.getId(), response.getBody().id());
        }
    }

    @Nested
    class UpdateOrigem {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();
            repository.save(origem);

            var request = OrigemUtils.buildOrigemRequest();

            // When
            var response = controller.updateOrigem(origem.getId(), request);

            // Then
            assertNotNull(response);
            assertNotNull(response.getBody());
            assertEquals(request.nome(), response.getBody().nome());
        }
    }

    @Nested
    class DeleteOrigem {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();
            repository.save(origem);

            // When
            var response = controller.deleteOrigem(origem.getId());

            // Then
            assertNotNull(response);
            assertEquals(204, response.getStatusCode().value());
        }
    }

}