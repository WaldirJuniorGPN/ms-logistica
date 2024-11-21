package com.techchallenge4.ms_logistica.domain;

import com.techchallenge4.ms_logistica.enums.RotaStatusEnum;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "rota")
public class Rota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private RotaStatusEnum status;

    @ManyToOne
    @JoinColumn(name = "origem_id")
    private Origem origem;

    @OneToOne
    @JoinColumn(name = "entregador_id")
    private Entregador entregador;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "rota_id")
    private List<Parada> paradas;

    public Optional<Parada> getParadaByPedidoId(Long pedidoId) {
        return this.paradas.stream()
                .filter(parada -> parada.getPedidoId().equals(pedidoId))
                .findFirst();
    }

}
