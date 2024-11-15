package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;

import java.util.List;

public interface EntregadorService {

    EntregadorResponse create(EntregadorRequest request);

    EntregadorResponse update(Long id, EntregadorRequest request);

    EntregadorResponse findById(Long id);

    void delete(Long id);

    List<EntregadorResponse> findAll();

}
