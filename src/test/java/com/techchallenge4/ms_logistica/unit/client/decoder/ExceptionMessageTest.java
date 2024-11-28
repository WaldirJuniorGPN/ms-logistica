package com.techchallenge4.ms_logistica.unit.client.decoder;

import com.techchallenge4.ms_logistica.client.decoder.ExceptionMessage;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ExceptionMessageTest {

    @Nested
    class TestExceptionMessage {
        @Test
        void testExceptionMessageConstructor() {
            // Given
            var exceptionMessage = new ExceptionMessage();
            var timestamp = "2023-11-27T21:35:00";
            var status = 400;
            var error = "Bad Request";
            var message = "Invalid request";
            var path = "/api/test";

            // When
            exceptionMessage.setTimestamp(timestamp);
            exceptionMessage.setStatus(status);
            exceptionMessage.setError(error);
            exceptionMessage.setMessage(message);
            exceptionMessage.setPath(path);

            // Then
            assertEquals(timestamp, exceptionMessage.getTimestamp());
            assertEquals(status, exceptionMessage.getStatus());
            assertEquals(error, exceptionMessage.getError());
            assertEquals(message, exceptionMessage.getMessage());
            assertEquals(path, exceptionMessage.getPath());
        }
    }

}