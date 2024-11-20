package com.techchallenge4.ms_logistica.repository;

import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {

    List<Entregador> findByEstadoAndDisponivel(EstadoEnum estado, boolean disponivel);

}
