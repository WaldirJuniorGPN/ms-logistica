package com.techchallenge4.ms_logistica.domain;

import com.techchallenge4.ms_logistica.enums.EstadoEnum;
import com.techchallenge4.ms_logistica.enums.RegiaoEnum;
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
@Entity(name = "origem")
public class Origem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String endereco;

    private Double latitude;

    private Double longitude;

    private String cep;

    private EstadoEnum estado;

    private RegiaoEnum regiao;

}
