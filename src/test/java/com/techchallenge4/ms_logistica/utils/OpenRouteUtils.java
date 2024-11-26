package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OpenRouteUtils {

    public static OptimizeResponse.Route buildOptimizeRoute(String type, List<Double> location, Integer job) {
        return OptimizeResponse.Route.builder()
                .steps(List.of(buildOptimizeRouteStep(type, location, job)))
                .build();
    }

    public static OptimizeResponse.Route.Step buildOptimizeRouteStep(String type, List<Double> location, Integer job) {
        return OptimizeResponse.Route.Step.builder()
                .type(type)
                .location(location)
                .job(job)
                .build();
    }

}
