package com.techchallenge4.ms_logistica.repository;

import com.techchallenge4.ms_logistica.domain.Origem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrigemRepository extends JpaRepository<Origem, Long> {
}
