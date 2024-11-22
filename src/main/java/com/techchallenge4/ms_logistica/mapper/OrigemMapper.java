package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.OrigemRequest;
import com.techchallenge4.ms_logistica.api.v1.response.OrigemResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.enums.RegiaoEnum;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface OrigemMapper {

    @Mapping(target = "estado", source = "request.cep", qualifiedByName = "cepToEstado")
    @Mapping(target = "regiao", source = "request.cep", qualifiedByName = "cepToRegiao")
    Origem toEntity(OrigemRequest request);

    OrigemResponse toResponse(Origem origem);

    void updateEntityFromRequest(OrigemRequest request, @MappingTarget Origem origem);

    List<OrigemResponse> toResponseList(List<Origem> origens);

    @Named("cepToEstado")
    default EstadoEnum cepToEstado(String cep) {
        return EstadoEnum.getByCep(cep);
    }

    @Named("cepToRegiao")
    default RegiaoEnum cepToRegiao(String cep) {
        return EstadoEnum.getByCep(cep).getRegiao();
    }

}
