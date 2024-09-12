package br.com.api_eco_feira.dto.produtoprodutor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoProdutorResponse {

    private Long idProduto;
    private String nome;
    private double valorCusto;
    private double valorVenda;
    private String grupoProdutos;
    private boolean apareceEmDemandas;


}