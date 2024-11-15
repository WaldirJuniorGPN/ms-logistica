package com.techchallenge4.ms_logistica.client.fallback;

import com.techchallenge4.ms_logistica.client.OpenRouteServiceClient;
import com.techchallenge4.ms_logistica.client.request.DirectionsRequest;
import com.techchallenge4.ms_logistica.client.request.OptimizeRequest;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenRouteServiceFallback implements OpenRouteServiceClient {

    @Override
    public OptimizeResponse getOptimizeRoute(OptimizeRequest request) {
        log.error("Error fetching route optimization from OpenRouteServiceClient");
        return null;
    }

    @Override
    public DirectionsResponse getDirections(String profile, DirectionsRequest request) {
        log.error("Error fetching directions from OpenRouteServiceClient");
        return null;
    }

}
