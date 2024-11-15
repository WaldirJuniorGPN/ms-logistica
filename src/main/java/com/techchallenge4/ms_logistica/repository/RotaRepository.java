package com.techchallenge4.ms_logistica.repository;

import com.techchallenge4.ms_logistica.domain.Rota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RotaRepository extends JpaRepository<Rota, Long> {

    @Query("SELECT r FROM rota r JOIN r.paradas p WHERE p.pedidoId = :pedidoId")
    Optional<Rota> findByPedidoId(@Param("pedidoId") Long pedidoId);

    Optional<List<Rota>> findAllByEntregadorId(Long entregadorId);

}
