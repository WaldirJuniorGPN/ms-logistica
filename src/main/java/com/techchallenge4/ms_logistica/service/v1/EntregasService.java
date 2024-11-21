package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;

public interface EntregasService {

    void processarPedidosPorEstado(EstadoEnum state);

    void finalizarPedidos();

}
