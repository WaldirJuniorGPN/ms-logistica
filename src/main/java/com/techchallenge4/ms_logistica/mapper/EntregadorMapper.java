package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Entregador;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface EntregadorMapper {

    Entregador toEntity(EntregadorRequest request);

    EntregadorResponse toResponse(Entregador entregador);

    void updateEntityFromRequest(EntregadorRequest request, @MappingTarget Entregador entregador);

    List<EntregadorResponse> toResponseList(List<Entregador> entregadores);

}
