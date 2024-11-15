package com.techchallenge4.ms_logistica.client.response;

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
        private double distance;
        private double duration;
        private List<Segment> segments;
        private Geometry geometry;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Segment {
            private double distance;
            private double duration;
            private String instruction;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Geometry {
            private String type;
            private List<List<Double>> coordinates;
        }
    }
}

