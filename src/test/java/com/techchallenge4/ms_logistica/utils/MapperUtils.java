package com.techchallenge4.ms_logistica.utils;

import com.techchallenge4.ms_logistica.mapper.EntregadorMapper;
import com.techchallenge4.ms_logistica.mapper.OpenRouteMapper;
import com.techchallenge4.ms_logistica.mapper.OrigemMapper;
import com.techchallenge4.ms_logistica.mapper.ParadaMapper;
import com.techchallenge4.ms_logistica.mapper.RastreamentoMapper;
import com.techchallenge4.ms_logistica.mapper.RotaMapper;
import com.techchallenge4.ms_logistica.mapper.RotaMapperImpl;
import lombok.NoArgsConstructor;
import org.mapstruct.factory.Mappers;

@NoArgsConstructor
public class MapperUtils {

    public static EntregadorMapper entregadorMapper() {
        return Mappers.getMapper(EntregadorMapper.class);
    }

    public static OpenRouteMapper openRouteMapper() {
        return Mappers.getMapper(OpenRouteMapper.class);
    }

    public static OrigemMapper origemMapper() {
        return Mappers.getMapper(OrigemMapper.class);
    }

    public static ParadaMapper paradaMapper() {
        return Mappers.getMapper(ParadaMapper.class);
    }

    public static RastreamentoMapper rastreamentoMapper() {
        return Mappers.getMapper(RastreamentoMapper.class);
    }

    public static RotaMapper rotaMapper() {
        return new RotaMapperImpl(
                paradaMapper(),
                origemMapper()
        );
    }

}
