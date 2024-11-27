package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class OpenRouteUtils {

    public static OptimizeResponse.Route buildOptimizeRoute(String type, List<Double> location, Integer job) {
        return OptimizeResponse.Route.builder()
                .vehicle(1)
                .cost(100)
                .delivery(List.of(1, 2))
                .amount(List.of(3, 4))
                .pickup(List.of(5, 6))
                .setup(10)
                .service(20)
                .duration(30)
                .waitingTime(5)
                .priority(1)
                .steps(List.of(buildOptimizeRouteStep(type, location, job)))
                .build();
    }

    public static OptimizeResponse.Route.Step buildOptimizeRouteStep(String type, List<Double> location, Integer job) {
        return OptimizeResponse.Route.Step.builder()
                .type(type)
                .location(location)
                .id(1)
                .setup(10)
                .service(20)
                .waitingTime(5)
                .job(job)
                .load(List.of(1, 2))
                .arrival(100)
                .duration(200)
                .build();
    }

    public static DirectionsResponse buildDirectionsResponse() {
        return DirectionsResponse.builder()
                .routes(List.of(buildRoute()))
                .build();
    }

    private static DirectionsResponse.Route buildRoute() {
        return DirectionsResponse.Route.builder()
                .summary(buildDirectionsRouteSummary())
                .segments(List.of(buildDirectionsRouteSegment()))
                .build();
    }

    public static OptimizeResponse buildOptimizeResponse() {
        return OptimizeResponse.builder()
                .code(200)
                .summary(buildOptimizeSummary())
                .routes(List.of(buildOptimizeRoute()))
                .build();
    }

    private static OptimizeResponse.Route buildOptimizeRoute() {
        return OptimizeResponse.Route.builder()
                .vehicle(1)
                .cost(100)
                .delivery(List.of(1, 2))
                .amount(List.of(3, 4))
                .pickup(List.of(5, 6))
                .setup(10)
                .service(20)
                .duration(30)
                .waitingTime(5)
                .priority(1)
                .steps(List.of(buildOptimizeRouteStep()))
                .build();
    }

    private static OptimizeResponse.Route.Step buildOptimizeRouteStep() {
        return OptimizeResponse.Route.Step.builder()
                .type("start")
                .location(List.of(1.0, 2.0))
                .job(1)
                .id(1)
                .setup(10)
                .service(20)
                .waitingTime(5)
                .load(List.of(1, 2))
                .arrival(100)
                .duration(200)
                .build();
    }

    private static OptimizeResponse.Summary buildOptimizeSummary() {
        return OptimizeResponse.Summary.builder()
                .cost(100)
                .routes(1)
                .unassigned(0)
                .delivery(List.of(1, 2))
                .amount(List.of(3, 4))
                .pickup(List.of(5, 6))
                .setup(10)
                .service(20)
                .duration(30)
                .waitingTime(5)
                .priority(1)
                .computingTimes(OptimizeResponse.Summary.ComputingTimes.builder()
                        .loading(10)
                        .solving(20)
                        .routing(30)
                        .build())
                .build();
    }

    private static DirectionsResponse.Route.Summary buildDirectionsRouteSummary() {
        return DirectionsResponse.Route.Summary.builder()
                .distance(100.0)
                .duration(200.0)
                .build();
    }

    private static DirectionsResponse.Route.Segment buildDirectionsRouteSegment() {
        return DirectionsResponse.Route.Segment.builder()
                .distance(50.0)
                .duration(100.0)
                .build();
    }

}