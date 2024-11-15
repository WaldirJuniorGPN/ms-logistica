package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.client.response.OptimizeResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Parada;
import com.techchallenge4.ms_logistica.domain.Rota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(config = MappingConfig.class)
public interface RouteMapper {

    String JOB = "job";

    @Mapping(target = "origem", ignore = true)
    @Mapping(target = "paradas", source = "route.steps", qualifiedByName = "mapParadas")
    Rota toRota(OptimizeResponse.Route route);

    @Named("mapParadas")
    default List<Parada> mapParadas(List<OptimizeResponse.Route.Step> steps) {
        return steps.stream()
                .filter(step -> JOB.equalsIgnoreCase(step.getType()))
                .map(step -> createParadaFromStep(step, steps.indexOf(step) + 1))  // Passing 1-based index
                .collect(Collectors.toList());
    }

    default Parada createParadaFromStep(OptimizeResponse.Route.Step step, long sequencia) {
        var location = step.getLocation();
        var latitude = location.get(0);
        var longitude = location.get(1);

        return Parada.builder()
                .sequencia(sequencia)
                .latitude(latitude)
                .longitude(longitude)
                .pedidoId(step.getJob() != null ? String.valueOf(step.getJob()) : null)
                .build();
    }
}
