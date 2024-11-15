package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.response.ParadaResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Parada;
import org.mapstruct.Mapper;

@Mapper(config = MappingConfig.class)
public interface ParadaMapper {

    ParadaResponse toResponse(Parada parada);

}
