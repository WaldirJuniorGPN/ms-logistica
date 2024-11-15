package com.techchallenge4.ms_logistica.repository;

import com.techchallenge4.ms_logistica.domain.Entregador;
import com.techchallenge4.ms_logistica.enums.CepEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EntregadorRepository extends JpaRepository<Entregador, Long> {

    Optional<Entregador> findByCepEnum(CepEnum state);

}
