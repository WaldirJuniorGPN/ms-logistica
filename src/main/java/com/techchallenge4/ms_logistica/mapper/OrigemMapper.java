package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.OrigemRequest;
import com.techchallenge4.ms_logistica.api.v1.response.OrigemResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.enums.RegionEnum;
import com.techchallenge4.ms_logistica.enums.StateEnum;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface OrigemMapper {

    @Mapping(target = "cep", source = "cep")
    @Mapping(target = "stateEnum", source = "cep", qualifiedByName = "cepToStateEnum")
    @Mapping(target = "regionEnum", source = "cep", qualifiedByName = "cepToRegionEnum")
    Origem toEntity(OrigemRequest request);

    OrigemResponse toResponse(Origem origem);

    void updateEntityFromRequest(OrigemRequest request, @MappingTarget Origem origem);

    List<OrigemResponse> toResponseList(List<Origem> origens);

    @Named("cepToStateEnum")
    default StateEnum cepToStateEnum(String cep) {
        return StateEnum.getByCep(cep);
    }

    @Named("cepToRegionEnum")
    default RegionEnum cepToRegionEnum(String cep) {
        return StateEnum.getByCep(cep).getRegionEnum();
    }

}
