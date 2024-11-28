package com.techchallenge4.ms_logistica.unit.client.decoder;

import com.techchallenge4.ms_logistica.client.decoder.CustomErrorDecoder;
import com.techchallenge4.ms_logistica.exception.FeignClientException;
import feign.Request;
import feign.Response;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

class CustomErrorDecoderTest {

    private final CustomErrorDecoder decoder = new CustomErrorDecoder();

    @Nested
    class Decode {
        @Test
        void shouldReturnFeignClientExceptionWithStatus400() {
            // Given
            var json = "{\"timestamp\":\"2023-11-27T21:35:00\",\"status\":400,\"error\":\"Bad Request\",\"message\":\"Invalid request\",\"path\":\"/api/test\"}";
            var request = mock(Request.class);
            var response = Response.builder()
                    .status(400)
                    .reason("Bad Request")
                    .headers(Collections.emptyMap())
                    .body(json, StandardCharsets.UTF_8)
                    .request(request)
                    .build();

            // When
            var exception = decoder.decode("methodKey", response);

            // Then
            assertInstanceOf(FeignClientException.class, exception);
            var feignClientException = (FeignClientException) exception;
            assertEquals("Invalid request", feignClientException.getMessage());
            assertEquals(400, feignClientException.getStatus());
        }
        @Test
        void shouldReturnFeignClientExceptionWithStatus401() {
            // Given
            var json = "{\"timestamp\":\"2023-11-27T21:35:00\",\"status\":401,\"error\":\"Unauthorized\",\"message\":\"Unauthorized request\",\"path\":\"/api/test\"}";
            var request = mock(Request.class);
            var response = Response.builder()
                    .status(401)
                    .reason("Unauthorized")
                    .headers(Collections.emptyMap())
                    .body(json, StandardCharsets.UTF_8)
                    .request(request)
                    .build();

            // When
            var exception = decoder.decode("methodKey", response);

            // Then
            assertInstanceOf(FeignClientException.class, exception);
            var feignClientException = (FeignClientException) exception;
            assertEquals("Unauthorized request", feignClientException.getMessage());
            assertEquals(401, feignClientException.getStatus());
        }
        @Test
        void shouldReturnFeignClientExceptionWithStatus403() {
            // Given
            var json = "{\"timestamp\":\"2023-11-27T21:35:00\",\"status\":403,\"error\":\"Forbidden\",\"message\":\"Forbidden request\",\"path\":\"/api/test\"}";
            var request = mock(Request.class);
            var response = Response.builder()
                    .status(403)
                    .reason("Forbidden")
                    .headers(Collections.emptyMap())
                    .body(json, StandardCharsets.UTF_8)
                    .request(request)
                    .build();

            // When
            var exception = decoder.decode("methodKey", response);

            // Then
            assertInstanceOf(FeignClientException.class, exception);
            var feignClientException = (FeignClientException) exception;
            assertEquals("Forbidden request", feignClientException.getMessage());
            assertEquals(403, feignClientException.getStatus());
        }
        @Test
        void shouldReturnFeignClientExceptionWithStatus404() {
            // Given
            var json = "{\"timestamp\":\"2023-11-27T21:35:00\",\"status\":404,\"error\":\"Not Found\",\"message\":\"Resource not found\",\"path\":\"/api/test\"}";
            var request = mock(Request.class);
            var response = Response.builder()
                    .status(404)
                    .reason("Not Found")
                    .headers(Collections.emptyMap())
                    .body(json, StandardCharsets.UTF_8)
                    .request(request)
                    .build();

            // When
            var exception = decoder.decode("methodKey", response);

            // Then
            assertInstanceOf(FeignClientException.class, exception);
            var feignClientException = (FeignClientException) exception;
            assertEquals("Resource not found", feignClientException.getMessage());
            assertEquals(404, feignClientException.getStatus());
        }
        @Test
        void shouldReturnFeignClientExceptionWithStatus500() {
            // Given
            var json = "{\"timestamp\":\"2023-11-27T21:35:00\",\"status\":500,\"error\":\"Internal Server Error\",\"message\":\"Internal server error\",\"path\":\"/api/test\"}";
            var request = mock(Request.class);
            var response = Response.builder()
                    .status(500)
                    .reason("Internal Server Error")
                    .headers(Collections.emptyMap())
                    .body(json, StandardCharsets.UTF_8)
                    .request(request)
                    .build();

            // When
            var exception = decoder.decode("methodKey", response);

            // Then
            assertInstanceOf(FeignClientException.class, exception);
            var feignClientException = (FeignClientException) exception;
            assertEquals("Internal server error", feignClientException.getMessage());
            assertEquals(500, feignClientException.getStatus());
        }
        @Test
        void shouldReturnDefaultExceptionForUnhandledStatus() {
            // Given
            var request = mock(Request.class);
            var response = Response.builder()
                    .status(413)
                    .reason("I'm a teapot")
                    .headers(Collections.emptyMap())
                    .body("I'm a teapot", StandardCharsets.UTF_8)
                    .request(request)
                    .build();

            // When
            var exception = decoder.decode("methodKey", response);

            // Then
            assertInstanceOf(Exception.class, exception);
        }
    }
}