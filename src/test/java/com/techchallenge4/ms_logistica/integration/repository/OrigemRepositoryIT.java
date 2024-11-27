package com.techchallenge4.ms_logistica.integration.repository;

import com.techchallenge4.ms_logistica.integration.AbstractContainerIT;
import com.techchallenge4.ms_logistica.repository.OrigemRepository;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
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
class OrigemRepositoryIT extends AbstractContainerIT {

    @Autowired
    private OrigemRepository repository;

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Nested
    class Save {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();

            // When
            repository.save(origem);
            var origens = repository.findAll();

            // Then
            assertEquals(1, origens.size());
            assertEquals(origem, origens.getFirst());
        }
    }

    @Nested
    class FindAll {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();

            // When
            repository.save(origem);
            var origens = repository.findAll();

            // Then
            assertEquals(1, origens.size());
            assertEquals(origem, origens.getFirst());
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();

            // When
            repository.save(origem);
            var origemSalva = repository.findById(origem.getId()).orElse(null);

            // Then
            assertEquals(origem, origemSalva);
        }
    }

    @Nested
    class FindByRegiao {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();

            // When
            repository.save(origem);
            var origemSalva = repository.findByRegiao(origem.getRegiao());

            // Then
            assertTrue(origemSalva.isPresent());
            assertEquals(origem, origemSalva.get());
        }
    }

    @Nested
    class Update {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();

            // When
            repository.save(origem);
            origem.setNome("Novo nome");
            repository.save(origem);
            var origens = repository.findAll();

            // Then
            assertEquals(1, origens.size());
            assertEquals(origem, origens.getFirst());
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldExecuteSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigemSemId();

            // When
            repository.save(origem);
            repository.delete(origem);
            var origens = repository.findAll();

            // Then
            assertEquals(0, origens.size());
        }
    }
}