package com.techchallenge4.ms_logistica.unit.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.OrigemRequest;
import com.techchallenge4.ms_logistica.api.v1.response.OrigemResponse;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.RegiaoEnum;
import com.techchallenge4.ms_logistica.mapper.OrigemMapper;
import com.techchallenge4.ms_logistica.utils.OrigemUtils;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class OrigemMapperTest {

    private final OrigemMapper mapper = Mappers.getMapper(OrigemMapper.class);

    @Nested
    class ToEntity {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var request = OrigemUtils.buildOrigemRequest();

            // When
            var result = mapper.toEntity(request);

            // Then
            assertNotNull(result);
            assertEquals(request.nome(), result.getNome());
            assertEquals(request.endereco(), result.getEndereco());
            assertEquals(request.latitude(), result.getLatitude());
            assertEquals(request.longitude(), result.getLongitude());
            assertEquals(request.cep(), result.getCep());
        }
        @Test
        void shouldMapCepToEstado() {
            // Given
            var request = OrigemUtils.buildOrigemRequest();
            var estadoEsperado = EstadoEnum.getByCep(request.cep());

            // When
            var result = mapper.toEntity(request);

            // Then
            assertNotNull(result);
            assertEquals(estadoEsperado, result.getEstado());
        }

        @Test
        void shouldMapCepToRegiao() {
            // Given
            var request = OrigemUtils.buildOrigemRequest();
            var regiaoEsperada = EstadoEnum.getByCep(request.cep()).getRegiao();

            // When
            var result = mapper.toEntity(request);

            // Then
            assertNotNull(result);
            assertEquals(regiaoEsperada, result.getRegiao());
        }
    }

    @Nested
    class ToResponse {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigem();

            // When
            var result = mapper.toResponse(origem);

            // Then
            validateResponse(result, origem);
        }
    }

    @Nested
    class UpdateEntityFromRequest {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var origem = OrigemUtils.buildOrigem();
            var request = OrigemRequest.builder()
                    .nome(origem.getNome() + " atualizado")
                    .endereco(origem.getEndereco() + " atualizado")
                    .latitude(origem.getLatitude() + 10.0)
                    .longitude(origem.getLongitude() - 10.0)
                    .cep(origem.getCep().replace("0", "1"))
                    .build();

            // When
            mapper.updateEntityFromRequest(request, origem);

            // Then
            assertEquals(request.nome(), origem.getNome());
            assertEquals(request.cep(), origem.getCep());
            assertEquals(request.latitude(), origem.getLatitude());
            assertEquals(request.longitude(), origem.getLongitude());
            assertEquals(EstadoEnum.getByCep(request.cep()), origem.getEstado());
            assertEquals(EstadoEnum.getByCep(request.cep()).getRegiao(), origem.getRegiao());
        }
    }

    @Nested
    class ToResponseList {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var origens = OrigemUtils.buildOrigemList(3);

            // When
            var result = mapper.toResponseList(origens);

            // Then
            assertNotNull(result);
            assertEquals(origens.size(), result.size());
            for (int i = 0; i < origens.size(); i++) {
                validateResponse(result.get(i), origens.get(i));
            }
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
            assertEquals(EstadoEnum.SP, result);
        }
    }

    @Nested
    class CepToRegiao {
        @Test
        void shouldMapCepToRegiao() {
            // Given
            var cepFromSP = "02435-060";

            // When
            var result = mapper.cepToRegiao(cepFromSP);

            // Then
            assertNotNull(result);
            assertEquals(RegiaoEnum.SUDESTE, result);
        }
    }

    private static void validateResponse(OrigemResponse result, Origem origem) {
        assertNotNull(result);
        assertEquals(origem.getId(), result.id());
        assertEquals(origem.getNome(), result.nome());
        assertEquals(origem.getEndereco(), result.endereco());
        assertEquals(origem.getLatitude(), result.latitude());
        assertEquals(origem.getLongitude(), result.longitude());
        assertEquals(origem.getCep(), result.cep());
        assertEquals(origem.getEstado(), result.estado());
        assertEquals(origem.getRegiao(), result.regiao());
    }
}
