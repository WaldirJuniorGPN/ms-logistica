package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.StateEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface EntregadorMapper {

    @Mapping(target = "stateEnum", source = "cep", qualifiedByName = "cepToStateEnum")
    Entregador toEntity(EntregadorRequest request);

    EntregadorResponse toResponse(Entregador entregador);

    void updateEntityFromRequest(EntregadorRequest request, @MappingTarget Entregador entregador);

    List<EntregadorResponse> toResponseList(List<Entregador> entregadores);

    @Named("cepToStateEnum")
    default StateEnum cepToStateEnum(String cep) {
        return StateEnum.getByCep(cep);
    }

}
