package com.techchallenge4.ms_logistica.client.fallback;

import com.techchallenge4.ms_logistica.client.OpenRouteServiceClient;
import com.techchallenge4.ms_logistica.client.request.OptimizeRequest;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OpenRouteServiceFallback implements OpenRouteServiceClient {

    @Override
    public OptimizeResponse optimizeRoute(OptimizeRequest request) {
        log.error("Error fetching route optimization from OpenRouteServiceClient");
        return null;
    }

}
