package br.com.api_eco_feira.dto.demandaprodutosassociados;

import br.com.api_eco_feira.enumerador.UnidadeMedida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class DemandaProdutoResponse {
    private Long idDemandaProduto;
    private String produtoPrefeitura;
    private double quantidade;
    private UnidadeMedida unidadeMedida;
    private double valorPrefeitura;
    private double saldo;
}
