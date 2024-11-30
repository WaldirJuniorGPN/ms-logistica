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
        private Long id;
        private List<Double> start;
        private List<Double> end;
        private List<Integer> capacity;
        private String profile;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Job {
        private Long id;
        private List<Double> location;
        private Integer service;
        private List<Integer> amount;
    }

}
