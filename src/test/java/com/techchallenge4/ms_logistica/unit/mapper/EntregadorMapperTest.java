package com.techchallenge4.ms_logistica.unit.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.mapper.EntregadorMapper;
import com.techchallenge4.ms_logistica.utils.EntregadorUtils;
import com.techchallenge4.ms_logistica.utils.MapperUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EntregadorMapperTest {

    private final EntregadorMapper mapper = MapperUtils.entregadorMapper();

    @Nested
    class ToEntity {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var request = EntregadorUtils.buildEntregadorRequest();

            // When
            var result = mapper.toEntity(request);

            // Then
            assertNotNull(result);
            assertEquals(request.nome(), result.getNome());
            assertEquals(request.cpf(), result.getCpf());
            assertEquals(request.contato(), result.getContato());
            assertEquals(request.capacidade(), result.getCapacidade());
            assertEquals(request.cep(), result.getCep());
        }

        @Test
        void shouldReturnNullWhenRequestIsNull() {
            // When
            var result = mapper.toEntity(null);

            // Then
            assertNull(result);
        }
    }

    @Nested
    class ToResponse {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregador();

            // When
            var result = mapper.toResponse(entregador);

            // Then
            validateResponse(result, entregador);
        }

        @Test
        void shouldReturnNullWhenEntregadorIsNull() {
            // When
            var result = mapper.toResponse(null);

            // Then
            assertNull(result);
        }
    }

    @Nested
    class UpdateEntityFromRequest {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entregador = EntregadorUtils.buildEntregador();
            var request = EntregadorRequest.builder()
                    .nome(entregador.getNome() + "1")
                    .cpf(entregador.getCpf() + "1")
                    .contato(entregador.getContato() + "1")
                    .capacidade(entregador.getCapacidade() + 1)
                    .cep(entregador.getCep().replace("0", "1"))
                    .build();

            // When
            mapper.updateEntityFromRequest(request, entregador);

            // Then
            assertEquals(request.nome(), entregador.getNome());
            assertEquals(request.cpf(), entregador.getCpf());
            assertEquals(request.contato(), entregador.getContato());
            assertEquals(request.capacidade(), entregador.getCapacidade());
            assertEquals(request.cep(), entregador.getCep());
        }

        @Test
        void shouldDoNothingWhenRequestIsNull() {
            // Given
            var entregador = EntregadorUtils.buildEntregador();

            // When
            mapper.updateEntityFromRequest(null, entregador);

            // Then
            assertNotNull(entregador);
        }

        @Test
        void shouldDoNothingWhenEntregadorIsNull() {
            // Given
            var request = EntregadorUtils.buildEntregadorRequest();

            // When & Then
            assertThrows(NullPointerException.class, () -> mapper.updateEntityFromRequest(request, null));
        }
    }

    @Nested
    class ToResponseList {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var entregadores = EntregadorUtils.buildEntregadorList(3);

            // When
            var result = mapper.toResponseList(entregadores);

            // Then
            assertNotNull(result);
            assertEquals(entregadores.size(), result.size());
            for (int i = 0; i < entregadores.size(); i++) {
                validateResponse(result.get(i), entregadores.get(i));
            }
        }

        @Test
        void shouldReturnNullWhenEntregadoresIsNull() {
            // When
            var result = mapper.toResponseList(null);

            // Then
            assertNull(result);
        }
    }

    @Nested
    class CepToEstado {
        @Test
        void shouldMapCepToEstadoSuccessfully() {
            // Given
            var cepFromSP = "02435-060";

            // When
            var result = mapper.cepToEstado(cepFromSP);

            // Then
            assertNotNull(result);
            assertEquals(EstadoEnum.getByCep(cepFromSP), result);
        }

        @Test
        void shouldReturnNullWhenCepIsNull() {
            // When & Then
            assertThrows(IllegalArgumentException.class, () -> mapper.cepToEstado(null));
        }
    }

    private static void validateResponse(EntregadorResponse result, Entregador entregador) {
        assertNotNull(result);
        assertEquals(entregador.getId(), result.id());
        assertEquals(entregador.getNome(), result.nome());
        assertEquals(entregador.getCpf(), result.cpf());
        assertEquals(entregador.getContato(), result.contato());
        assertEquals(entregador.getCapacidade(), result.capacidade());
        assertEquals(entregador.getCep(), result.cep());
        assertEquals(entregador.getEstado(), result.estado());
        assertEquals(entregador.getDisponivel(), result.disponivel());
    }
}