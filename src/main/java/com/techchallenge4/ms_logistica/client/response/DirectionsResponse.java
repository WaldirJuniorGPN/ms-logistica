package com.techchallenge4.ms_logistica.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionsResponse {

    private List<Route> routes;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Route {

        private Summary summary;
        private List<Segment> segments;
        private List<Double> bbox;
        private String geometry;
        @JsonProperty("way_points")
        private List<Integer> wayPoints;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Summary {

            private Double distance;
            private Double duration;

        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Segment {

            private Double distance;
            private Double duration;
            private List<Step> steps;

            @Data
            @Builder
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Step {

                private Double distance;
                private Double duration;
                private Integer type;
                private String instruction;
                private String name;
                @JsonProperty("way_points")
                private List<Integer> wayPoints;

            }
        }
    }
}

