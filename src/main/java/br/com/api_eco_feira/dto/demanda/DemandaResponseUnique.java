package br.com.api_eco_feira.dto.demanda;

import br.com.api_eco_feira.enumerador.StatusDemanda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandaResponseUnique {

    private Long id;
    private LocalDateTime dataCriacao;
    private double valorTotalPrefeitura;
    private LocalDate prazoMaximo;
    private StatusDemanda statusDemanda;
    private String descricao;

}
