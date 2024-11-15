package com.techchallenge4.ms_logistica.client.response;

import lombok.Data;

import java.util.List;

@Data
public class OptimizeResponse {
    private Integer code;
    private Summary summary;
    private List<Route> routes;

    @Data
    public static class Summary {
        private Integer cost;
        private Integer routes;
        private Integer unassigned;
        private List<Integer> delivery;
        private List<Integer> amount;
        private List<Integer> pickup;
        private Integer setup;
        private Integer service;
        private Integer duration;
        private Integer waiting_time;
        private Integer priority;
        private List<Object> violations;
        private ComputingTimes computing_times;

        @Data
        public static class ComputingTimes {
            private Integer loading;
            private Integer solving;
            private Integer routing;
        }
    }

    @Data
    public static class Route {
        private Integer vehicle;
        private Integer cost;
        private List<Integer> delivery;
        private List<Integer> amount;
        private List<Integer> pickup;
        private Integer setup;
        private Integer service;
        private Integer duration;
        private Integer waiting_time;
        private Integer priority;
        private List<Step> steps;
        private List<Object> violations;

        @Data
        public static class Step {
            private String type;
            private List<Double> location;
            private Integer id;
            private Integer setup;
            private Integer service;
            private Integer waiting_time;
            private Integer job;
            private List<Integer> load;
            private Integer arrival;
            private Integer duration;
            private List<Object> violations;
        }
    }
}
