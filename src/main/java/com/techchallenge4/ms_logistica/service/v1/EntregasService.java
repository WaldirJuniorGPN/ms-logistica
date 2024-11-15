package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.enums.CepEnum;

public interface EntregasService {

    void processDeliveriesForState(CepEnum state);

}
