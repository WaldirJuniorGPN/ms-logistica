package com.techchallenge4.ms_logistica.client.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DirectionsResponse {

    private List<Route> routes;

    @Getter
    @Builder
    public static class Route {

        private Summary summary;
        private List<Segment> segments;
        private List<Double> bbox;
        private String geometry;
        @JsonProperty("way_points")
        private List<Integer> wayPoints;

        @Getter
        @Builder
        public static class Summary {

            private Double distance;
            private Double duration;

        }

        @Getter
        @Builder
        public static class Segment {

            private Double distance;
            private Double duration;
            private List<Step> steps;

            @Getter
            @Builder
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

