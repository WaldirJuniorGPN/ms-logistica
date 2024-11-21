package com.techchallenge4.ms_logistica.domain;

import com.techchallenge4.ms_logistica.enums.PedidoStatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static java.util.Objects.nonNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "parada")
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pedidoId;

    private Long sequencia;

    private Double latitude;

    private Double longitude;

    private String contato;

    private PedidoStatusEnum status;

    private String observacao;

    public boolean isEntregaFinalizada() {
        return nonNull(this.status) && (this.status.equals(PedidoStatusEnum.ENTREGUE) || this.status.equals(PedidoStatusEnum.CANCELADO));
    }

}
