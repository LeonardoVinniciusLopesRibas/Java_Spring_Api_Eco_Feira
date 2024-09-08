package br.com.api_eco_feira.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoProdutorRequest {

    private String nome;
    private double valorCusto;
    private double valorVenda;
    private Long grupoProdutos;
    private Long idEmpresa;
    private boolean apareceEmDemandas;
    private boolean ativo;

}
