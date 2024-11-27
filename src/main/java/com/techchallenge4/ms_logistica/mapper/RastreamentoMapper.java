package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.RastreamentoRequest;
import com.techchallenge4.ms_logistica.api.v1.response.RastreamentoResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Rastreamento;
import com.techchallenge4.ms_logistica.domain.Rota;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MappingConfig.class)
public interface RastreamentoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rota", source = "rota")
    @Mapping(target = "ultimaLatitude", source = "request.latitude")
    @Mapping(target = "ultimaLongitude", source = "request.longitude")
    Rastreamento toEntity(RastreamentoRequest request, Rota rota);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "rota", ignore = true)
    @Mapping(target = "ultimaLatitude", source = "request.latitude")
    @Mapping(target = "ultimaLongitude", source = "request.longitude")
    void updateUltimaPosicao(@MappingTarget Rastreamento rastreamento, RastreamentoRequest request);

    @Mapping(target = "rotaId", source = "rastreamento.rota.id")
    RastreamentoResponse toResponse(Rastreamento rastreamento);

}
