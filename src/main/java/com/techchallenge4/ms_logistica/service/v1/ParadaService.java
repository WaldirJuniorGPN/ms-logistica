package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.api.v1.request.ParadaStatusRequest;
import com.techchallenge4.ms_logistica.api.v1.response.ParadaResponse;

public interface ParadaService {

    ParadaResponse patchStatus(Long id, ParadaStatusRequest request);

}
