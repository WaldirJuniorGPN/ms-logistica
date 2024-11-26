package com.techchallenge4.ms_logistica.unit.client.fallback;

import com.techchallenge4.ms_logistica.client.fallback.OpenRouteClientFallback;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

class OpenRouteClientFallbackTest {

    private final OpenRouteClientFallback fallback = new OpenRouteClientFallback();

    @Nested
    class GetOptimizeRoute {
        @Test
        void getOptimizeRouteFallback() {
            // Given
            var response = fallback.getOptimizeRoute(null);

            // When & Then
            assertNull(response);
        }
    }

    @Nested
    class GetDirections {
        @Test
        void getDirectionsFallback() {
            // Given
            var response = fallback.getDirections(null, null);

            // When & Then
            assertNull(response);
        }
    }

}