package com.techchallenge4.ms_logistica.unit.service.v1.impl;

import com.techchallenge4.ms_logistica.client.PedidoClient;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.ParadaMapper;
import com.techchallenge4.ms_logistica.repository.ParadaRepository;
import com.techchallenge4.ms_logistica.service.v1.ParadaService;
import com.techchallenge4.ms_logistica.service.v1.impl.ParadaServiceImpl;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import com.techchallenge4.ms_logistica.utils.ParadaUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ParadaServiceImplTest {

    private final ParadaRepository repository = mock(ParadaRepository.class);
    private final ParadaMapper mapper = spy(MapperUtils.paradaMapper());
    private final PedidoClient pedidoClient = mock(PedidoClient.class);
    private final ParadaService service = new ParadaServiceImpl(repository, mapper, pedidoClient);

    @Nested
    class PatchStatus {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var id = 1L;
            var parada = ParadaUtils.buildParada();
            var request = ParadaUtils.buildParadaStatusRequest();

            when(repository.findById(id)).thenReturn(Optional.of(parada));
            when(pedidoClient.updatePedidoStatus(parada.getPedidoId(), request.status())).thenReturn(null);
            when(repository.save(parada)).thenReturn(parada);

            // When
            service.patchStatus(id, request);

            // Then
            assertEquals(request.status(), parada.getStatus());
            verify(repository, times(1)).findById(id);
            verify(pedidoClient, times(1)).updatePedidoStatus(parada.getPedidoId(), request.status());
            verify(mapper, times(1)).updateStatus(parada, request);
            verify(repository, times(1)).save(parada);
            verify(mapper, times(1)).toResponse(parada);
        }
        @Test
        void shouldThrowResourceNotFoundException() {
            // Given
            var id = 1L;

            when(repository.findById(id)).thenReturn(Optional.empty());

            // When
            var exception = assertThrows(ResourceNotFoundException.class, () -> service.patchStatus(id, null));

            // Then
            assertEquals("Parada não encontrada pelo id: " + id, exception.getMessage());
            verify(repository, times(1)).findById(id);
        }
    }

}
