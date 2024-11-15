package br.com.api_eco_feira.dto.demandaprodutosassociados;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DemandaProdutosAssociadosDtoRequest {

    private Long demanda;
    private Long produto;
    private Long prefeitura;
    private double quantidade;
    private double valorPrefeitura;

}
