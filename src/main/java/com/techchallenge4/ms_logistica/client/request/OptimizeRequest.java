package com.techchallenge4.ms_logistica.client.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OptimizeRequest {

    private List<Vehicle> vehicles;
    private List<Job> jobs;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Vehicle {
        private String id;
        private List<Double> start;
        private List<Double> end;
        private Integer capacity;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Job {
        private String id;
        private List<String> location;
        private Integer service;
        private Integer amount;
    }

}
