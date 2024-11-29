package com.techchallenge4.ms_logistica.mapper;

import com.techchallenge4.ms_logistica.client.request.DirectionsRequest;
import com.techchallenge4.ms_logistica.client.request.OptimizeRequest;
import com.techchallenge4.ms_logistica.client.response.PedidoResponse;
import com.techchallenge4.ms_logistica.configuration.MappingConfig;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.domain.Parada;
import com.techchallenge4.ms_logistica.domain.Rastreamento;
import com.techchallenge4.ms_logistica.domain.Rota;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;
import java.util.stream.Stream;

@Mapper(config = MappingConfig.class)
public interface OpenRouteMapper {

    @Mapping(target = "vehicles", expression = "java(mapVehicle(entregador, origem))")
    @Mapping(target = "jobs", source = "pedidoResponseList", qualifiedByName = "mapJobs")
    OptimizeRequest toOptimizeRequest(List<PedidoResponse> pedidoResponseList, Entregador entregador, @Context Origem origem);

    default List<OptimizeRequest.Vehicle> mapVehicle(Entregador entregador, @Context Origem origem) {
        OptimizeRequest.Vehicle vehicle = OptimizeRequest.Vehicle.builder()
                .id(String.valueOf(entregador.getId()))
                .start(List.of(origem.getLongitude(), origem.getLatitude()))
                .end(List.of(origem.getLongitude(), origem.getLatitude()))
                .capacity(entregador.getCapacidade())
                .build();
        return List.of(vehicle);
    }

    @Named("mapJobs")
    default List<OptimizeRequest.Job> mapJobs(List<PedidoResponse> pedidoResponses) {
        return pedidoResponses.stream()
                .map(pedido -> OptimizeRequest.Job.builder()
                        .id(String.valueOf(pedido.id()))
                        .location(List.of(pedido.endereco().longitude(), pedido.endereco().latitude()))
                        .service(300)
                        .amount(pedido.quantidade())
                        .build())
                .toList();
    }

    @Mapping(target = "coordinates", source = "rota", qualifiedByName = "mapCoordinates")
    DirectionsRequest toDirectionsRequestFromRota(Rota rota);

    @Named("mapCoordinates")
    default List<List<Double>> mapCoordinates(Rota rota) {
        return Stream.concat(
                Stream.of(List.of(rota.getOrigem().getLongitude(), rota.getOrigem().getLatitude())),
                rota.getParadas().stream().map(parada -> List.of(parada.getLongitude(), parada.getLatitude()))
        ).toList();
    }

    @Mapping(target = "coordinates", expression = "java(mapCoordinates(rastreamento, parada))")
    DirectionsRequest toDirectionsRequestFromRastreamentoAndParada(Rastreamento rastreamento, Parada parada);

    default List<List<Double>> mapCoordinates(Rastreamento rastreamento, Parada parada) {
        return List.of(
                List.of(rastreamento.getUltimaLongitude(), rastreamento.getUltimaLatitude()),
                List.of(parada.getLongitude(), parada.getLatitude())
        );
    }

}
