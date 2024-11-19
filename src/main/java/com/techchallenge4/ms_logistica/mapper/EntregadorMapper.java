package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface EntregadorMapper {

    @Mapping(target = "estado", source = "cep", qualifiedByName = "cepToEstado")
    Entregador toEntity(EntregadorRequest request);

    EntregadorResponse toResponse(Entregador entregador);

    void updateEntityFromRequest(EntregadorRequest request, @MappingTarget Entregador entregador);

    List<EntregadorResponse> toResponseList(List<Entregador> entregadores);

    @Named("cepToEstado")
    default EstadoEnum cepToEstado(String cep) {
        return EstadoEnum.getByCep(cep);
    }

}
