package com.techchallenge4.ms_logistica.client;

import com.techchallenge4.ms_logistica.client.request.DirectionsRequest;
import com.techchallenge4.ms_logistica.client.request.OptimizeRequest;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.configuration.OpenRouteServiceClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "open-route-service", url = "${services.open-route-service.url}", configuration = OpenRouteServiceClientConfig.class)
public interface OpenRouteServiceClient {

    @PostMapping(value = "/optimization")
    OptimizeResponse getOptimizeRoute(@RequestBody OptimizeRequest request);

    @PostMapping(value = "/v2/directions/{profile}")
    DirectionsResponse getDirections(@PathVariable("profile") String profile, @RequestBody DirectionsRequest request);

}

