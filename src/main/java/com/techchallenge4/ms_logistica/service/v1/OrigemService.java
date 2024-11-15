package com.techchallenge4.ms_logistica.service.v1;

import com.techchallenge4.ms_logistica.api.v1.request.OrigemRequest;
import com.techchallenge4.ms_logistica.api.v1.response.OrigemResponse;

import java.util.List;

public interface OrigemService {

    OrigemResponse create(OrigemRequest request);

    OrigemResponse findById(Long id);

    OrigemResponse update(Long id, OrigemRequest request);

    void delete(Long id);

    List<OrigemResponse> findAll();

}
