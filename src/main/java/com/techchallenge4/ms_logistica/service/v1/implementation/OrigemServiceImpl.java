package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.api.v1.request.OrigemRequest;
import com.techchallenge4.ms_logistica.api.v1.response.OrigemResponse;
import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.exception.ResourceNotFound;
import com.techchallenge4.ms_logistica.mapper.OrigemMapper;
import com.techchallenge4.ms_logistica.repository.OrigemRepository;
import com.techchallenge4.ms_logistica.service.v1.OrigemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrigemServiceImpl implements OrigemService {

    private final OrigemRepository repository;
    private final OrigemMapper mapper;

    @Override
    public OrigemResponse create(OrigemRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public List<OrigemResponse> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public OrigemResponse findById(Long id) {
        return mapper.toResponse(getOrigemById(id));
    }

    @Override
    public OrigemResponse update(Long id, OrigemRequest request) {
        var origem = getOrigemById(id);
        mapper.updateEntityFromRequest(request, origem);
        return mapper.toResponse(repository.save(origem));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private Origem getOrigemById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFound("Origem not found"));
    }

}
