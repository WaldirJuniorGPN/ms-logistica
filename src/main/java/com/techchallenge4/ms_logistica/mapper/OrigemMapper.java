package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.api.v1.request.OrigemRequest;
import com.techchallenge4.ms_logistica.api.v1.response.OrigemResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Origem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(config = MappingConfig.class)
public interface OrigemMapper {

    Origem toEntity(OrigemRequest request);

    OrigemResponse toResponse(Origem origem);

    void updateEntityFromRequest(OrigemRequest request, @MappingTarget Origem origem);

    List<OrigemResponse> toResponseList(List<Origem> origens);
}
