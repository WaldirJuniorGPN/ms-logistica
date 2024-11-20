package com.techchallenge4.ms_logistica.domain;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "entregador")
public class Entregador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String cpf;

    private String contato;

    private Integer capacidade;

    private String cep;

    private EstadoEnum estado;

    private Boolean disponivel;

}
