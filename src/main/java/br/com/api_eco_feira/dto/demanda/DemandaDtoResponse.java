package br.com.api_eco_feira.dto.demanda;

import br.com.api_eco_feira.enumerador.StatusDemanda;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandaDtoResponse {

    private Long idDemanda;
    private String descricao;
    private double valorTotalPrefeitura;
    private LocalDate prazoMaximo;
    private StatusDemanda statusDemanda;

}
