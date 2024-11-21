package com.techchallenge4.ms_logistica.service.v1.implementation;

import com.techchallenge4.ms_logistica.api.v1.request.EntregadorRequest;
import com.techchallenge4.ms_logistica.api.v1.response.EntregadorResponse;
import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.exception.ResourceNotFoundException;
import com.techchallenge4.ms_logistica.mapper.EntregadorMapper;
import com.techchallenge4.ms_logistica.repository.EntregadorRepository;
import com.techchallenge4.ms_logistica.service.v1.EntregadorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EntregadorServiceImpl implements EntregadorService {

    private final EntregadorRepository repository;
    private final EntregadorMapper mapper;

    @Override
    public EntregadorResponse create(EntregadorRequest request) {
        return mapper.toResponse(repository.save(mapper.toEntity(request)));
    }

    @Override
    public EntregadorResponse findById(Long id) {
        return mapper.toResponse(getEntregadorById(id));
    }

    @Override
    public List<EntregadorResponse> findAll() {
        return mapper.toResponseList(repository.findAll());
    }

    @Override
    public List<Entregador> findByEstadoAndDisponibilidade(EstadoEnum estado, boolean disponibilidade) {
        var entregadores = repository.findByEstadoAndDisponivel(estado, disponibilidade);
        if (entregadores.isEmpty()) {
            throw new ResourceNotFoundException("Nenhum Entregador encontrado para o estado: " + estado + " e disponibilidade: " + disponibilidade);
        }
        return entregadores;
    }

    @Override
    public EntregadorResponse update(Long id, EntregadorRequest request) {
        var entregador = getEntregadorById(id);
        mapper.updateEntityFromRequest(request, entregador);
        return mapper.toResponse(repository.save(entregador));
    }

    @Override
    public void bloquearEntregador(Entregador entregador) {
        updateDisponibilidade(entregador, false);
    }

    @Override
    public void liberarEntregador(Entregador entregador) {
        updateDisponibilidade(entregador, true);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    private void updateDisponibilidade(Entregador entregador, boolean disponivel) {
        entregador.setDisponivel(disponivel);
        repository.save(entregador);
    }

    private Entregador getEntregadorById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entregador nao encontrado"));
    }

}
