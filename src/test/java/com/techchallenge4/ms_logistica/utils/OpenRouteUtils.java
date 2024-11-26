package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.client.request.OptimizeRequest;
import com.techchallenge4.ms_logistica.client.response.DirectionsResponse;
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

    public static DirectionsResponse buildDirectionsResponse() {
        return DirectionsResponse.builder()
                .routes(List.of(
                        DirectionsResponse.Route.builder()
                                .distance(100.0)
                                .duration(200.0)
                                .segments(List.of(
                                        DirectionsResponse.Route.Segment.builder()
                                                .distance(50.0)
                                                .duration(100.0)
                                                .instruction("Turn right")
                                                .build(),
                                        DirectionsResponse.Route.Segment.builder()
                                                .distance(50.0)
                                                .duration(100.0)
                                                .instruction("Turn left")
                                                .build()
                                ))
                                .geometry(DirectionsResponse.Route.Geometry.builder()
                                        .type("LineString")
                                        .coordinates(List.of(
                                                List.of(1.0, 2.0),
                                                List.of(3.0, 4.0)
                                        ))
                                        .build())
                                .build()
                ))
                .build();
    }

    public static OptimizeRequest buildOptimizeRequest() {
        return OptimizeRequest.builder()
                .jobs(List.of(
                        OptimizeRequest.Job.builder()
                                .location(List.of(1.0, 2.0))
                                .build()
                ))
                .build();
    }

    public static OptimizeResponse buildOptimizeResponse() {
        return OptimizeResponse.builder()
                .routes(List.of(
                        OptimizeResponse.Route.builder()
                                .steps(List.of(
                                        OptimizeResponse.Route.Step.builder()
                                                .type("start")
                                                .location(List.of(1.0, 2.0))
                                                .job(1)
                                                .build(),
                                        OptimizeResponse.Route.Step.builder()
                                                .type("end")
                                                .location(List.of(3.0, 4.0))
                                                .job(2)
                                                .build()
                                ))
                                .build()
                ))
                .build();
    }

}
