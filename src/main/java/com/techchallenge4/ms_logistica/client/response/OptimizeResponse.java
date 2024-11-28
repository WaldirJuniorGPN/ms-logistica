package com.techchallenge4.ms_logistica.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OptimizeResponse {
    private Integer code;
    private Summary summary;
    private List<Route> routes;

    @Getter
    @Builder
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
        @JsonProperty("waiting_time")
        private Integer waitingTime;
        private Integer priority;
        @JsonProperty("computing_times")
        private ComputingTimes computingTimes;

        @Getter
        @Builder
        public static class ComputingTimes {
            private Integer loading;
            private Integer solving;
            private Integer routing;
        }
    }

    @Getter
    @Builder
    public static class Route {
        private Integer vehicle;
        private Integer cost;
        private List<Integer> delivery;
        private List<Integer> amount;
        private List<Integer> pickup;
        private Integer setup;
        private Integer service;
        private Integer duration;
        @JsonProperty("waiting_time")
        private Integer waitingTime;
        private Integer priority;
        private List<Step> steps;

        @Getter
        @Builder
        public static class Step {
            private String type;
            private List<Double> location;
            private Integer id;
            private Integer setup;
            private Integer service;
            @JsonProperty("waiting_time")
            private Integer waitingTime;
            private Integer job;
            private List<Integer> load;
            private Integer arrival;
            private Integer duration;
        }
    }
}
