package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.RastreamentoRequest;
import com.techchallenge4.ms_logistica.api.v1.response.RastreamentoResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Rastreamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MappingConfig.class)
public interface RastreamentoMapper {

    Rastreamento toEntity(RastreamentoRequest request);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rota", ignore = true)
    @Mapping(target = "ultimaLatitude", source = "request.latitude")
    @Mapping(target = "ultimaLongitude", source = "request.longitude")
    void updateUltimaPosicao(@MappingTarget Rastreamento rastreamento, RastreamentoRequest request);

    RastreamentoResponse toResponse(Rastreamento rastreamento);

}
