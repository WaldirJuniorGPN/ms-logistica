package com.techchallenge4.ms_logistica.repository;

import com.techchallenge4.ms_logistica.domain.Rastreamento;
import com.techchallenge4.ms_logistica.domain.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RastreamentoRepository extends JpaRepository<Rastreamento, Long> {

    Optional<Rastreamento> findByRota(Rota rota);

}
