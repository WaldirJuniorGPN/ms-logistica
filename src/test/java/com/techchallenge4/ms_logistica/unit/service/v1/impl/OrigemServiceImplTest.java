package com.techchallenge4.ms_logistica.unit.service.v1.impl;

import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.enums.RegiaoEnum;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.OrigemMapper;
import com.techchallenge4.ms_logistica.repository.OrigemRepository;
import com.techchallenge4.ms_logistica.service.v1.OrigemService;
import com.techchallenge4.ms_logistica.service.v1.impl.OrigemServiceImpl;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrigemServiceImplTest {

    private final OrigemRepository repository = mock(OrigemRepository.class);
    private final OrigemMapper mapper = spy(MapperUtils.origemMapper());
    private final OrigemService service = new OrigemServiceImpl(repository, mapper);

    @Nested
    class Create {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var request = OrigemUtils.buildOrigemRequest();
            var entity = OrigemUtils.buildOrigem();
            var response = OrigemUtils.buildOrigemResponse();

            when(repository.save(any(Origem.class))).thenReturn(entity);
            doReturn(response).when(mapper).toResponse(entity);

            // When
            var result = service.create(request);

            // Then
            assertNotNull(result);
            assertEquals(response, result);

            verify(repository, times(1)).save(any(Origem.class));
            verify(mapper, times(1)).toResponse(entity);
        }
    }

    @Nested
    class FindAll {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entities = List.of(OrigemUtils.buildOrigem());
            var responses = List.of(OrigemUtils.buildOrigemResponse());

            when(repository.findAll()).thenReturn(entities);
            doReturn(responses).when(mapper).toResponseList(entities);

            // When
            var result = service.findAll();

            // Then
            assertNotNull(result);
            assertEquals(responses, result);

            verify(repository, times(1)).findAll();
            verify(mapper, times(1)).toResponseList(entities);
        }
    }

    @Nested
    class FindByRegiao {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var regiao = RegiaoEnum.SUL;
            var entity = OrigemUtils.buildOrigem();

            when(repository.findByRegiao(regiao)).thenReturn(java.util.Optional.of(entity));

            // When
            var result = service.findByRegiao(regiao);

            // Then
            assertNotNull(result);
            assertEquals(entity, result);

            verify(repository, times(1)).findByRegiao(regiao);
        }

        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var regiao = RegiaoEnum.SUL;

            when(repository.findByRegiao(regiao)).thenReturn(java.util.Optional.empty());

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.findByRegiao(regiao));

            verify(repository, times(1)).findByRegiao(regiao);
        }
    }

    @Nested
    class FindById {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var id = 1L;
            var entity = OrigemUtils.buildOrigem();
            var response = OrigemUtils.buildOrigemResponse();

            when(repository.findById(id)).thenReturn(java.util.Optional.of(entity));
            doReturn(response).when(mapper).toResponse(entity);

            // When
            var result = service.findById(id);

            // Then
            assertNotNull(result);
            assertEquals(response, result);

            verify(repository, times(1)).findById(id);
            verify(mapper, times(1)).toResponse(entity);
        }

        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var id = 1L;

            when(repository.findById(id)).thenReturn(Optional.empty());

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.findById(id));

            verify(repository, times(1)).findById(id);
        }
    }

    @Nested
    class Update {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var id = 1L;
            var request = OrigemUtils.buildOrigemRequest();
            var entity = OrigemUtils.buildOrigem();
            var response = OrigemUtils.buildOrigemResponse();

            when(repository.findById(id)).thenReturn(Optional.of(entity));
            when(repository.save(any(Origem.class))).thenReturn(entity);
            doReturn(response).when(mapper).toResponse(entity);

            // When
            var result = service.update(id, request);

            // Then
            assertNotNull(result);
            assertEquals(response, result);

            verify(repository, times(1)).findById(id);
            verify(repository, times(1)).save(any(Origem.class));
            verify(mapper, times(1)).updateEntityFromRequest(request, entity);
            verify(mapper, times(1)).toResponse(entity);
        }

        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var id = 1L;
            var request = OrigemUtils.buildOrigemRequest();

            when(repository.findById(id)).thenReturn(Optional.empty());

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.update(id, request));

            verify(repository, times(1)).findById(id);
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

            doThrow(new ResourceNotFoundException("Origem not found")).when(repository).deleteById(id);

            // When / Then
            assertThrows(ResourceNotFoundException.class, () -> service.delete(id));

            verify(repository, times(1)).deleteById(id);
        }
    }

}
