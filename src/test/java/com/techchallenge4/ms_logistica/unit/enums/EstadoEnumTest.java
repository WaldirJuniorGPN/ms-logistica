package com.techchallenge4.ms_logistica.unit.enums;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EstadoEnumTest {

    @Nested
    class GetByCep {
        @Test
        void shouldReturnSuccessfully() {
            // Given
            var cep = "12345678";

            // When
            var estado = EstadoEnum.getByCep(cep);

            // Then
            assertNotNull(estado);
            assertEquals(EstadoEnum.SP, estado);
        }
        @Test
        void shouldThrowIllegalArgumentExceptionWhenCepIsNull() {
            // When
            var exception = assertThrows(IllegalArgumentException.class, () -> EstadoEnum.getByCep(null));

            // Then
            assertEquals("CEP inválido", exception.getMessage());
        }
        @Test
        void shouldThrowIllegalArgumentExceptionWhenCepLengthIsInvalid() {
            // Given
            var cep = "999";

            // When
            var exception = assertThrows(IllegalArgumentException.class, () -> EstadoEnum.getByCep(cep));

            // Then
            assertEquals("CEP inválido", exception.getMessage());
        }
        @Test
        void shouldThrowIllegalArgumentExceptionWhenCepIsNotFound() {
            // Given
            var cep = "00999";

            // When
            var exception = assertThrows(IllegalArgumentException.class, () -> EstadoEnum.getByCep(cep));

            // Then
            assertEquals("CEP não corresponse a nenhum estado", exception.getMessage());
        }
    }

}
