package com.techchallenge4.ms_logistica.repository;

import com.techchallenge4.ms_logistica.domain.Origem;
import com.techchallenge4.ms_logistica.enums.CepEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrigemRepository extends JpaRepository<Origem, Long> {

    Optional<Origem> findByCepEnum(CepEnum state);

}
