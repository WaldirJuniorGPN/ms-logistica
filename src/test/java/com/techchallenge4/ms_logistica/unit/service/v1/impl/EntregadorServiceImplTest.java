package com.techchallenge4.ms_logistica.unit.service.v1.impl;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.EntregadorMapper;
import com.techchallenge4.ms_logistica.repository.EntregadorRepository;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import com.techchallenge4.ms_logistica.service.v1.impl.EntregadorServiceImpl;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EntregadorServiceImplTest {

    private final EntregadorRepository repository = mock(EntregadorRepository.class);
    private final EntregadorMapper mapper = spy(MapperUtils.entregadorMapper());
    private final EntregadorService service = new EntregadorServiceImpl(repository, mapper);

    @Nested
    class Create {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var request = EntregadorUtils.buildEntregadorRequest();
            var entity = EntregadorUtils.buildEntregador();

            when(repository.save(any(Entregador.class))).thenReturn(entity);

            // When
            var result = service.create(request);

            // Then
            assertNotNull(result);
            assertEquals(entity.getId(), result.id());

            verify(repository, times(1)).save(any(Entregador.class));
            verify(mapper, times(1)).toEntity(any(EntregadorRequest.class));
            verify(mapper, times(1)).toResponse(any(Entregador.class));
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var id = 1L;
            var entity = EntregadorUtils.buildEntregador();

            when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));

            // When
            var result = service.findById(id);

            // Then
            assertNotNull(result);
            assertEquals(entity.getId(), result.id());

            verify(repository, times(1)).findById(id);
            verify(mapper, times(1)).toResponse(entity);
        }

        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var id = 1L;

            when(repository.findById(id)).thenReturn(java.util.Optional.empty());

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.findById(id));

            verify(repository, times(1)).findById(id);
        }
    }

    @Nested
    class FindAll {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entities = List.of(EntregadorUtils.buildEntregador());

            when(repository.findAll()).thenReturn(entities);

            // When
            var result = service.findAll();

            // Then
            assertNotNull(result);
            assertEquals(entities.size(), result.size());

            verify(repository, times(1)).findAll();
            verify(mapper, times(1)).toResponseList(entities);
        }
    }

    @Nested
    class FindByEstadoAndDisponibilidade {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var estado = EstadoEnum.SP;
            var disponibilidade = true;
            var entities = List.of(EntregadorUtils.buildEntregador());

            when(repository.findByEstadoAndDisponivel(estado, disponibilidade)).thenReturn(entities);

            // When
            var result = service.findByEstadoAndDisponibilidade(estado, disponibilidade);

            // Then
            assertNotNull(result);
            assertEquals(entities.size(), result.size());

            verify(repository, times(1)).findByEstadoAndDisponivel(estado, disponibilidade);
        }

        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var estado = EstadoEnum.SP;
            var disponibilidade = true;

            when(repository.findByEstadoAndDisponivel(estado, disponibilidade)).thenReturn(List.of());

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.findByEstadoAndDisponibilidade(estado, disponibilidade));

            verify(repository, times(1)).findByEstadoAndDisponivel(estado, disponibilidade);
        }
    }

    @Nested
    class Update {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var id = 1L;
            var request = EntregadorUtils.buildEntregadorRequest();
            var entity = EntregadorUtils.buildEntregador();

            when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));
            when(repository.save(any(Entregador.class))).thenReturn(entity);

            // When
            var result = service.update(id, request);

            // Then
            assertNotNull(result);
            assertEquals(entity.getId(), result.id());

            verify(repository, times(1)).findById(id);
            verify(repository, times(1)).save(entity);
            verify(mapper, times(1)).updateEntityFromRequest(request, entity);
            verify(mapper, times(1)).toResponse(entity);
        }

        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var id = 1L;
            var request = EntregadorUtils.buildEntregadorRequest();

            when(repository.findById(id)).thenReturn(java.util.Optional.empty());

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.update(id, request));

            verify(repository, times(1)).findById(id);
        }
    }

    @Nested
    class BloquearEntregador {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entity = EntregadorUtils.buildEntregador();

            // When
            service.bloquearEntregador(entity);

            // Then
            assertEquals(false, entity.getDisponivel());

            verify(repository, times(1)).save(entity);
        }
    }

    @Nested
    class DesbloquearEntregador {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entity = EntregadorUtils.buildEntregador();

            // When
            service.liberarEntregador(entity);

            // Then
            assertEquals(true, entity.getDisponivel());

            verify(repository, times(1)).save(entity);
        }
    }

    @Nested
    class Delete {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var id = 1L;

            // When
            service.delete(id);

            // Then
            verify(repository, times(1)).deleteById(id);
        }

        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var id = 1L;

            doThrow(new ResourceNotFoundException("Entregador nao encontrado")).when(repository).deleteById(id);

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.delete(id));

            verify(repository, times(1)).deleteById(id);
        }
    }

}
